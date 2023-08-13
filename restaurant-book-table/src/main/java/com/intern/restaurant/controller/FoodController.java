package com.intern.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.restaurant.dto.FoodDTO;
import com.intern.restaurant.model.Food;
import com.intern.restaurant.model.User;
import com.intern.restaurant.service.FoodService;

@Controller
@RequestMapping("/food")
@CrossOrigin("*")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@GetMapping("/create/")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("food", new Food());
	     
	    return "food_form";
	}
	
	@PostMapping("/create/save/")
	public String foodCreate(Food food) {
        foodService.createFood(food);
        return "redirect:/food/list/admin/";
	}
	
	@GetMapping("/list/user/")
	@PreAuthorize("hasRole('USER')")
	public String listFoodUser(Model model) {
		List<FoodDTO> listFoodDto = foodService.getListFood();
		model.addAttribute("listFoodDto", listFoodDto);
		return "listfoods";
	}
	
	@GetMapping("/list/admin/")
	@PreAuthorize("hasRole('ADMIN')")
	public String listFoodAdmin(Model model) {
		List<FoodDTO> listFoodDto = foodService.getListFood();
		model.addAttribute("listFoodDto", listFoodDto);
		return "listfoodadmin";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable Integer id) {
        foodService.deleteById(id);
        return "redirect:/food/list/admin/";
    }
}
