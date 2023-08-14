package com.intern.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.restaurant.dto.CategoryDTO;
import com.intern.restaurant.dto.FoodDTO;
import com.intern.restaurant.model.Food;
import com.intern.restaurant.service.CategoryService;
import com.intern.restaurant.service.FoodService;

@Controller
@RequestMapping("/food")
@CrossOrigin("*")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/create/")
	public String showFoodForm(Model model) {
		List<CategoryDTO> listCategoryDto = categoryService.listCategory();
		model.addAttribute("listCategories", listCategoryDto);
	    model.addAttribute("food", new Food());
	     
	    return "food_form_add";
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
		return "food_list";
	}
	
	@GetMapping("/list/admin/")
	@PreAuthorize("hasRole('ADMIN')")
	public String listFoodAdmin(Model model) {
		List<FoodDTO> listFoodDto = foodService.getListFood();
		model.addAttribute("listFoodDto", listFoodDto);
		return "food_list_admin";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable Integer id) {
        foodService.deleteById(id);
        return "redirect:/food/list/admin/";
    }
	
	@GetMapping("/update/{id}")
	public String showFoodUpdateForm(@PathVariable int id, Model model) {
	    Food foodUpdate = foodService.getById(id);
		
		model.addAttribute("foodupdate", foodUpdate);
		
		List<CategoryDTO> listCategoryDto = categoryService.listCategory();
		model.addAttribute("listCategories", listCategoryDto);
	     
	    return "food_form_update";
	}
	
	@PostMapping("/update/save/")
	public String foodUpdate(@ModelAttribute Food food) {
		foodService.updateFood(food);
		return "redirect:/food/list/admin/";
	}
}
