package com.intern.restaurant.service;

import java.util.List;

import com.intern.restaurant.dto.FoodDTO;
import com.intern.restaurant.model.Food;

public interface FoodService {
	
	public Food createFood(Food food);
	
	public List<FoodDTO> getListFood();
	
	public boolean updateName(String fd_name, String newname);
	public boolean updateThumbnail(String fd_name, String thumbnail);
	public boolean updatePrice(String fd_name, int price);
	public boolean updateDescription(String fd_name, String description);
	public boolean updateYoutubeLink(String fd_name, String youtube_link);
	public boolean updateCf_id(String fd_name, int cf_id);
	public boolean updateFood(String fd_name,Food updateFood);
	
//	public void deleteByName(String fd_name);
	public void deleteById(int id);
	
	
}
