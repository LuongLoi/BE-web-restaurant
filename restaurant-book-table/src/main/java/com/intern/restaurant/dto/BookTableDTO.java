package com.intern.restaurant.dto;

import java.time.LocalDateTime;

import com.intern.restaurant.model.Food;
import com.intern.restaurant.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTableDTO {

	private int bt_id;
	private LocalDateTime datetime;
	private int numberPeople;
	private String note;
//	private int fd_id;
	private int us_id;
}
