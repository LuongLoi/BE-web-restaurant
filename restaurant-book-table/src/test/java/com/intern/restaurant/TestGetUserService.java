package com.intern.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intern.restaurant.dto.UserDTO;
import com.intern.restaurant.exception.UserNotFoundException;
import com.intern.restaurant.service.UserService;

@SpringBootTest
public class TestGetUserService {

	@Autowired
	private UserService userService;
	
	// Test case: No user record in users table in database 
//	@Test
//	public void testGetListUserNoRecord() {
//		assertThatThrownBy(() -> {
//			userService.getAllUser();
//		}).isInstanceOf(UserNotFoundException.class)
//		.hasMessageContaining("No record exists");
//		
//	}
	
	// Test case: Have user record in users table in database
//	@Test
//	public void testGetListUser() {
//		List<UserDTO> userDto = userService.getAllUser();
//		for (UserDTO result : userDto) {
//			System.out.println(result.toString());
//		}
//		assertThat(userDto).isNotNull();
//	}
//	
//	@Test
//	public void testGetUserById() {
//		assertThat(userService.getUserById(10)).isNotNull();
//		System.out.println(userService.getUserById(10));
//		assertThatThrownBy(() -> {
//			userService.getUserById(1000);
//		}).isInstanceOf(UserNotFoundException.class)
//		.hasMessageContaining("User not found");
//	}
//	
//	@Test
//	public void testGetUserByEmail() {
//		assertThat(userService.getUserByEmail("user05@gmail.com")).isNotNull();
//		assertThatThrownBy(() -> {
//			userService.getUserByEmail("nguyenvanbd");
//		}).isInstanceOf(UserNotFoundException.class)
//		.hasMessageContaining("User not found");
//	}
//	
//	@Test
//	public void testGetUserByMobile() {
//		assertThat(userService.getUserByMobile("0997654321")).isNotNull();
//		assertThatThrownBy(() -> {
//			userService.getUserByEmail("0123456789");
//		}).isInstanceOf(UserNotFoundException.class)
//		.hasMessageContaining("User not found");
//	}
	
	@Test
	public void testUpdatePassword() {
		assertThat(userService.updateUserPassword("user05", "matkhaucuataodo")).isTrue();
		assertThat(userService.updateUserPassword("nguyenvanabc", "matkhaucuataodo")).isFalse();

	}
}
