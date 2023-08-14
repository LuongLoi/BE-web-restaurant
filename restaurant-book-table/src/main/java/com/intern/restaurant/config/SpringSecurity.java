package com.intern.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService) {
    	DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
    	daoProvider.setUserDetailsService(detailsService);
    	daoProvider.setPasswordEncoder(passwordEncoder());
    	return new ProviderManager(daoProvider);
    } 
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	return http.csrf(csrf -> csrf.disable())
    				.authorizeHttpRequests(auth -> {
    					auth.requestMatchers("/index").permitAll();
    					auth.requestMatchers("/home/").permitAll();
    					auth.requestMatchers("/listusers/**").hasRole("ADMIN");
    					auth.requestMatchers("/update-role/**").hasRole("ADMIN");
    					auth.requestMatchers("/register/**").permitAll();
    					
    					auth.requestMatchers("/food/create/**").hasRole("ADMIN");
                        auth.requestMatchers("/food/update/**").hasRole("ADMIN");
                        auth.requestMatchers("/food/delete/**").hasRole("ADMIN");
                        auth.requestMatchers("food/list/admin/").hasRole("ADMIN");
                        auth.requestMatchers("/food/list/user/").permitAll();
                        
                        auth.requestMatchers("/category/create/**").hasRole("ADMIN");
                        auth.requestMatchers("/category/list/**").permitAll();
                        auth.requestMatchers("/category/update/**").hasRole("ADMIN");
                        auth.requestMatchers("/category/delete/**").hasRole("ADMIN");
                        
                        auth.requestMatchers("/post/create/**").hasAnyRole("ADMIN","USER");
                        auth.requestMatchers("/post/listpost/**").hasRole("ADMIN");
                        auth.requestMatchers("/post/list/**").hasAnyRole("ADMIN","USER");
                        auth.requestMatchers("/post/update/title/**").hasAnyRole("ADMIN","USER");
                        auth.requestMatchers("/post/update/thumbnail/**").hasAnyRole("ADMIN","USER");
                        auth.requestMatchers("/post/update/published/**").hasRole("ADMIN");
                        auth.requestMatchers("/post/delete/**").hasAnyRole("ADMIN","USER");
                        
                        auth.requestMatchers("/image/create/**").hasRole("ADMIN");
                        auth.requestMatchers("/image/list/**").hasAnyRole("ADMIN", "USER");
                        auth.requestMatchers("/image/list-by-food-id/**").hasAnyRole("ADMIN","USER");
                        auth.requestMatchers("/image/update/**").hasRole("ADMIN");
                        auth.requestMatchers("/image/delete/**").hasRole("ADMIN");
                        
                        auth.requestMatchers("/book-table/create/**").hasRole("USER");
                        auth.requestMatchers("/booking/list/**").hasRole("ADMIN");
    					
                        auth.requestMatchers("/admin_page/**").hasRole("ADMIN");
                        //auth.anyRequest().authenticated();
    				})
    				.formLogin(
    					    form -> form
    					        .loginPage("/login")
    					        .loginProcessingUrl("/login")
    					        .successHandler((request, response, authentication) -> {
    					            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
    					                response.sendRedirect("/admin_page/");
    					            } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
    					                response.sendRedirect("/home/");
    					            } else {
    					                throw new IllegalStateException("Unhandled role");
    					            }
    					        })
    					        .permitAll())
    				.httpBasic(withDefaults()).build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}