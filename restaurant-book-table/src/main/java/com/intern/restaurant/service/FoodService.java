package com.intern.restaurant.service;

import java.util.List;

import com.intern.restaurant.dto.FoodDTO;
import com.intern.restaurant.model.Food;

public interface FoodService {
	
	public Food createFood(Food food);
	
	public List<FoodDTO> getListFood();
	public void updateFood(Food updateFood);
	public void deleteById(int id);
	public Food getById(int id);
	public void deleteAllByCategoryId(int cf_id);
	
}
