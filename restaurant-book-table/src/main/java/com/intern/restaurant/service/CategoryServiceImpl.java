package com.intern.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.restaurant.dto.CategoryDTO;
import com.intern.restaurant.exception.CategoryException;
import com.intern.restaurant.exception.FoodException;
import com.intern.restaurant.mapper.CategoryMapper;
import com.intern.restaurant.model.Category;
import com.intern.restaurant.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDTO> listCategory() {
		// TODO Auto-generated method stub
		List<CategoryDTO> listCategoryDTOs = new ArrayList<>();
		for (Category category: categoryRepository.findAll()) {
			listCategoryDTOs.add(CategoryMapper.toCategoryDTO(category));
		}
		if (listCategoryDTOs.size() == 0)
			throw new CategoryException("Category not found");
		return listCategoryDTOs;
	}

	@Override
	public Category createCategory(Category category) {
		// TODO Auto-generated method stub
		Optional<Category> o_category = categoryRepository.findByName(category.getName());
		if (o_category.isPresent()) {
			throw new CategoryException("Category already exists");
		}
		return categoryRepository.save(category);
	}
	
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}

	@Override
	public void updateCategory(Category updateCategory) {
		// TODO Auto-generated method stub
		Optional<Category> o_category = categoryRepository.findById(updateCategory.getCf_id());
		if (o_category.isPresent()) {
			Category category = o_category.get();
			category.setCf_id(updateCategory.getCf_id());
			category.setDescription(updateCategory.getDescription());
			category.setImage_link(updateCategory.getImage_link());
			category.setName(updateCategory.getName());
			category.setType(updateCategory.getType());
			categoryRepository.save(category);
		}
		
	}

	@Override
	public Category getById(int cf_id) {
		// TODO Auto-generated method stub
		Optional<Category> o_category = categoryRepository.findById(cf_id);
		if (!o_category.isPresent())
			throw new FoodException("Category not found");
		return o_category.get();
	}
}
