package com.intern.restaurant.service;

import java.util.List;

import com.intern.restaurant.dto.CategoryDTO;
import com.intern.restaurant.model.Category;


public interface CategoryService {
	public List<CategoryDTO> listCategory();
	public Category createCategory(Category category); 
	public void updateCategory(Category updateCategory);
	public void deleteById(int id);
	public Category getById(int cf_id);
}
