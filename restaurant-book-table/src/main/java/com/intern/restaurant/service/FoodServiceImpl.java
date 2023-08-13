package com.intern.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		food.setCategory(new Category(1));
		Optional<Food> o_food = foodRepository.findByName(food.getName());
		
		if (o_food.isPresent())
			throw new FoodException("Food already exists");
		
		return foodRepository.save(food);
	}

	@Override
	public boolean updateName(String fd_name, String newname) {
		// TODO Auto-generated method stub
		Optional<Food> o_food = foodRepository.findByName(fd_name);
		if (o_food.isPresent()) {
			Food food = o_food.get();
			food.setUser(new User(userService.getCurrentUserLogin()));
			food.setName(newname);
			foodRepository.save(food);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateThumbnail(String fd_name, String thumbnail) {
		// TODO Auto-generated method stub
		Optional<Food> o_food = foodRepository.findByName(fd_name);
		if (o_food.isPresent()) {
			Food food = o_food.get();
			food.setUser(new User(userService.getCurrentUserLogin()));
			food.setThumbnail(thumbnail);
			foodRepository.save(food);
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePrice(String fd_name, int price) {
		// TODO Auto-generated method stub
		Optional<Food> o_food = foodRepository.findByName(fd_name);
		if (o_food.isPresent()) {
			Food food = o_food.get();
			food.setUser(new User(userService.getCurrentUserLogin()));
			food.setPrice(price);
			foodRepository.save(food);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDescription(String fd_name, String description) {
		// TODO Auto-generated method stub
		Optional<Food> o_food = foodRepository.findByName(fd_name);
		if (o_food.isPresent()) {
			Food food = o_food.get();
			food.setUser(new User(userService.getCurrentUserLogin()));
			food.setDescription(description);
			foodRepository.save(food);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateYoutubeLink(String fd_name, String youtube_link) {
		// TODO Auto-generated method stub
		Optional<Food> o_food = foodRepository.findByName(fd_name);
		if (o_food.isPresent()) {
			Food food = o_food.get();
			food.setUser(new User(userService.getCurrentUserLogin()));
			food.setYoutube_link(youtube_link);
			foodRepository.save(food);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCf_id(String fd_name, int cf_id) {
		// TODO Auto-generated method stub
		Optional<Food> o_food = foodRepository.findByName(fd_name);
		if (o_food.isPresent()) {
			Food food = o_food.get();
			food.setUser(new User(userService.getCurrentUserLogin()));
			food.setCategory(new Category(cf_id));
			foodRepository.save(food);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateFood(String fd_name,Food updateFood) {
		// TODO Auto-generated method stub
		Optional<Food> o_food = foodRepository.findByName(fd_name);
		if (o_food.isPresent()) {
			Food food = o_food.get();
			food.setUser(new User(userService.getCurrentUserLogin()));
			food.setCategory(new Category(updateFood.getCategory().getCf_id()));
			food.setName(updateFood.getName());
			food.setDescription(updateFood.getDescription());
			food.setPrice(updateFood.getPrice());
			food.setThumbnail(updateFood.getThumbnail());
			food.setYoutube_link(updateFood.getYoutube_link());
			foodRepository.save(food);
			return true;
		}
		return false;
	}

//	@Override
//	public void deleteByName(String fd_name) {
//		// TODO Auto-generated method stub
//		Optional<Food> o_food = foodRepository.findByName(fd_name);
//		if (!o_food.isPresent())
//			throw new FoodException("Food is not found");
//		int fd_id = o_food.get().getFd_id();
//		foodRepository.deleteById(fd_id);
//	}

	@Override
	public void deleteById(int id) {
		Optional<Food> o_food = foodRepository.findById(id);
		if (!o_food.isPresent())
			throw new FoodException("Food is not found");
		foodRepository.deleteById(id);
	}
	
	
}
