package com.intern.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.restaurant.dto.CategoryDTO;
import com.intern.restaurant.model.Category;
import com.intern.restaurant.service.CategoryService;
import com.intern.restaurant.service.FoodService;
import com.intern.restaurant.service.PostService;

@Controller
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private PostService postService; 
	
	@GetMapping("/create/")
	public String showCategoryForm(Model model) {
	    model.addAttribute("category", new Category());
	     
	    return "category_form_add";
	}
	
	@PostMapping("/create/save/")
	public String categoryCreate(Category category) {
        categoryService.createCategory(category);
        return "redirect:/category/list/";
	}
	
	@GetMapping("/list/")
	public String listFoodUser(Model model) {
		List<CategoryDTO> listCategoryDto = categoryService.listCategory();
		model.addAttribute("listCategoryDto", listCategoryDto);
		return "category_list";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable Integer id) {
		foodService.deleteAllByCategoryId(id);
		postService.deleteAllByCategoryId(id);
		categoryService.deleteById(id);
        return "redirect:/category/list/";
    }
	
	@GetMapping("/update/{id}")
	public String showCategoryUpdateForm(@PathVariable int id, Model model) {
	    Category categoryUpdate = categoryService.getById(id);
		model.addAttribute("categoryupdate", categoryUpdate);
	    return "category_form_update";
	}
	
	@PostMapping("/update/save/")
	public String categoryUpdate(@ModelAttribute Category category) {
		categoryService.updateCategory(category);
		return "redirect:/category/list/";
	}
}
