package com.intern.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.intern.restaurant.dto.UserDTO;
import com.intern.restaurant.service.UserService;


@Controller
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/admin_page/")
	public String showAdminPage() {
		return "admin_page";
	}
	
	@GetMapping("/home/")
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping("/users/")
	public String listUsers(Model model) {
	    List<UserDTO> listUsers = userService.getAllUser();
	    model.addAttribute("listUsers", listUsers);
	     
	    return "users";
	}
}
