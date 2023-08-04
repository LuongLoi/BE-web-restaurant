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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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
//        return http.csrf((csrf) -> csrf.disable())
//                .authorizeHttpRequests((authorize) -> {
//                	authorize.requestMatchers("/index").permitAll();
//                    authorize.requestMatchers("/register/**").permitAll();
//                    authorize.requestMatchers("/users/**").hasRole("ADMIN");
//                    authorize.requestMatchers("/delete/**").hasRole("ADMIN");
//                    authorize.requestMatchers("/food/create/**").permitAll();
//                    authorize.requestMatchers("/food/listfood/**").permitAll();
//                    authorize.anyRequest().authenticated();
//				}).formLogin(
//                        form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/users")
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                ).httpBasic(withDefaults())
//               .build();
    	return http.csrf(csrf -> csrf.disable())
    				.authorizeHttpRequests(auth -> {
    					auth.requestMatchers("/listusers/**").hasRole("ADMIN");
    					auth.requestMatchers("/update-role/**").hasRole("ADMIN");
    					auth.requestMatchers("/register/**").permitAll();
    					
    					auth.requestMatchers("/food/create/**").hasRole("ADMIN");
                        auth.requestMatchers("/food/listfood/**").permitAll();
                        auth.requestMatchers("/food/update/**").hasRole("ADMIN");
                        auth.requestMatchers("/food/delete/**").hasRole("ADMIN");
                        
                        auth.requestMatchers("/category/create/**").hasRole("ADMIN");
                        auth.requestMatchers("/category/list/**").permitAll();
                        auth.requestMatchers("/category/update/**").hasRole("ADMIN");
                        auth.requestMatchers("/category/delete/**").hasRole("ADMIN");
    					//auth.anyRequest().authenticated();
    				})
    				.httpBasic(withDefaults()).build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}