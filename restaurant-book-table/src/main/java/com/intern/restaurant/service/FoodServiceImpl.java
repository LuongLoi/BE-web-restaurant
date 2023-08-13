package com.intern.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.restaurant.dto.CategoryDTO;
import com.intern.restaurant.dto.FoodDTO;
import com.intern.restaurant.exception.FoodException;
import com.intern.restaurant.exception.FoodNotFoundException;
import com.intern.restaurant.mapper.FoodMapper;
import com.intern.restaurant.model.Category;
import com.intern.restaurant.model.Food;
import com.intern.restaurant.model.User;
import com.intern.restaurant.repository.FoodRepository;
@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<FoodDTO> getListFood() {
		// TODO Auto-generated method stub
		List<FoodDTO> listFood = new ArrayList<>();
		for (Food food : foodRepository.findAll()) {
			listFood.add(FoodMapper.toFoodDTO(food));
		}
		if (listFood.size() == 0)
			throw new FoodNotFoundException("Food is not found");
		return listFood;
	}

	@Override
	public Food createFood(Food food) {
		// TODO Auto-generated method stub
		food.setUser(new User(userService.getCurrentUserLogin()));
		food.setCategory(new Category(food.getCategory().getCf_id()));
		Optional<Food> o_food = foodRepository.findByName(food.getName());
		
		if (o_food.isPresent())
			throw new FoodException("Food already exists");
		
		return foodRepository.save(food);
	}

	@Override
	public void deleteById(int id) {
		Optional<Food> o_food = foodRepository.findById(id);
		if (!o_food.isPresent())
			throw new FoodException("Food is not found");
		foodRepository.deleteById(id);
	}
	
	@Override
	public void deleteAllByCategoryId(int cf_id) {
		for (Food food : foodRepository.findAll()) {
			if (food.getCategory().getCf_id() == cf_id) {
				foodRepository.deleteById(food.getFd_id());
			}
		}
	}

	@Override
	public void updateFood(Food updateFood) {
		// TODO Auto-generated method stub
		Optional<Food> o_food = foodRepository.findById(updateFood.getFd_id());
		if (o_food.isPresent()) {
			Food food = o_food.get();
			food.setFd_id(updateFood.getFd_id());
			food.setUser(new User(userService.getCurrentUserLogin()));
//			food.setCategory(new Category(updateFood.getCategory().getCf_id()));
			food.setCategory(updateFood.getCategory());
			food.setName(updateFood.getName());
			food.setDescription(updateFood.getDescription());
			food.setPrice(updateFood.getPrice());
			food.setThumbnail(updateFood.getThumbnail());
			food.setYoutube_link(updateFood.getYoutube_link());
			foodRepository.save(food);
		} 
	}
	
	public Food getById(int fd_id) {
		Optional<Food> o_food = foodRepository.findById(fd_id);
		if (!o_food.isPresent())
			throw new FoodException("Food not found");
		return o_food.get();
	}
	
	
	
}
