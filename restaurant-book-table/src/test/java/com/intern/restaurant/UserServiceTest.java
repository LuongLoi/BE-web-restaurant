package com.intern.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.intern.restaurant.exception.UserException;
import com.intern.restaurant.model.User;
import com.intern.restaurant.service.UserService;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testCreateUser() {
//		User user2 = userService.createUser("user02","user02","User 02","0988654321","user02@gmail.com","ha noi");
//		assertThat(user2).isNotNull();
//		User user3 = userService.createUser("user03", "user03", "User 03", "0997654321", "user03@gmail.com", "da nang");
//		assertThat(user3).isNotNull();
//		User user4 = userService.createUser("user04", "user04", "User 04", "0997654322", "user04@gmail.com", "da nang");
//		assertThat(user3).isNotNull();
//		User user5 = userService.createUser("user05", "user05", "User 05", "0997654323", "user05@gmail.com", "da nang");
//		assertThat(user5).isNotNull();
//		User user6 = userService.createUser("user06", "user06", "User 06", "0997654324", "user06@gmail.com", "da nang");
//		assertThat(user6).isNotNull();
//		User user7 = userService.createUser("user07", "user07", "User 07", "0997654325", "user07@gmail.com", "da nang");
//		assertThat(user7).isNotNull();
//		User user8 = userService.createUser("user08", "user08", "User 08", "0997654326", "user08@gmail.com", "da nang");
//		assertThat(user8).isNotNull();
	}
	
	@Test
	public void testCreateUserExists() {
//		assertThatThrownBy(() -> {
//			userService.createUser("user02","user02","User 02","0988654321","user02@gmail.com","ha noi");
//		}).isInstanceOf(UserException.class)
//		.hasMessageContaining("User already exists");
//		assertThatThrownBy(() -> {
//			userService.createUser("user03", "user03", "User 03", "0997654321", "user03@gmail.com", "da nang");
//		}).isInstanceOf(UserException.class)
//		.hasMessageContaining("User already exists");
//		assertThatThrownBy(() -> {
//			userService.createUser("user04", "user04", "User 04", "0997654322", "user04@gmail.com", "da nang");
//		}).isInstanceOf(UserException.class)
//		.hasMessageContaining("User already exists");
	}
	
	@Test
	public void testDeleteByID() {
		userService.deleteById(2);
		userService.deleteById(1000);
	}
	
	@Test
	public void testDeleteAll() {
		userService.deleteAll();
	}

}
