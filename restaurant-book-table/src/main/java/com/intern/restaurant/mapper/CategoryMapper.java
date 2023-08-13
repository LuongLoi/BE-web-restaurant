package com.intern.restaurant.mapper;

import com.intern.restaurant.dto.CategoryDTO;
import com.intern.restaurant.model.Category;

public class CategoryMapper {
	
	public static CategoryDTO toCategoryDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getCf_id());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		categoryDTO.setImage_link(category.getImage_link());
		categoryDTO.setType(category.getType());
		
		return categoryDTO;
	}
}
