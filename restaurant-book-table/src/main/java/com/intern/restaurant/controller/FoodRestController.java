package com.intern.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.restaurant.model.Food;
import com.intern.restaurant.service.FoodService;

@RestController
@RequestMapping("/food")
@CrossOrigin("*")
public class FoodRestController {
	
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/create/")
	public ResponseEntity<?> createFood(@RequestBody Food food) {;
		return ResponseEntity.ok(foodService.createFood(food));
	}
	
	@GetMapping("/listfood/")
	public ResponseEntity<?> getListFood() {
		return ResponseEntity.ok(foodService.getListFood());
	}
	
	@PutMapping("/update/name/{name}")
	public ResponseEntity<?> updateName(@PathVariable String name, @RequestBody String newname ){
		return ResponseEntity.ok(foodService.updateName(name, newname));
	}
	
	@PutMapping("/update/cf_id/{name}")
	public ResponseEntity<?> updateCf_id(@PathVariable String name, @RequestBody int newcf_id ) {
		return ResponseEntity.ok(foodService.updateCf_id(name, newcf_id));
	}
	
	@PutMapping("/update/thumbnail/{name}")
	public ResponseEntity<?> updateThumbnail(@PathVariable String name, @RequestBody String newthumbnail ) {
		return ResponseEntity.ok(foodService.updateThumbnail(name, newthumbnail));
	}
	
	@PutMapping("/update/price/{name}")
	public ResponseEntity<?> updatePrice(@PathVariable String name, @RequestBody int newprice ) {
		return ResponseEntity.ok(foodService.updatePrice(name, newprice));
	}
	
	@PutMapping("/update/description/{name}")
	public ResponseEntity<?> updateDescription(@PathVariable String name, @RequestBody String newdescription ) {
		return ResponseEntity.ok(foodService.updateDescription(name, newdescription));
	}
	
	@PutMapping("/update/youtube_link/{name}")
	public ResponseEntity<?> updateYoutubelink(@PathVariable String name, @RequestBody String youtubeLink ) {
		return ResponseEntity.ok(foodService.updateYoutubeLink(name, youtubeLink));
	}
	
	@PutMapping("/update/{name}")
	public ResponseEntity<?> updateFood(@PathVariable String name, @RequestBody Food updateFood ) {
		return ResponseEntity.ok(foodService.updateFood(name, updateFood));
	}
	
//	@DeleteMapping("/delete/{name}")
//	public void deleteByName(@PathVariable String name) {
//		foodService.deleteByName(name);
//	}
}
