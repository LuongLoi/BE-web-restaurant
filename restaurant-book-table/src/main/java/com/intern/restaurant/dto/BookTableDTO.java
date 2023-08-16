package com.intern.restaurant.dto;


import java.time.LocalDateTime;


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
	private int us_id;
}
