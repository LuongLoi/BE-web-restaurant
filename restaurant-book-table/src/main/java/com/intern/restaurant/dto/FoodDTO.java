package com.intern.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
	private int id;
	private String name;
	private String thumbnail;
	private int price;
	private String description;
	private String youtube_link;
	private int cf_id;
}
