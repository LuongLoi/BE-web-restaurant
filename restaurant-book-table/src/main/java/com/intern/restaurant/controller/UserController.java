package com.intern.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.restaurant.dto.UserDTO;
import com.intern.restaurant.service.UserService;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/listusers")
	public ResponseEntity<List<UserDTO>> getUsers() {
	    List<UserDTO> listUsers = userService.getAllUser();	     
	    return ResponseEntity.ok(listUsers);
	}
}