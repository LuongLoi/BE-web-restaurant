package com.intern.restaurant.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bt_id;
	
	@Column(name = "bt_datetime")
	private LocalDateTime datetime;
	
	@Column(name = "bt_number_people")
	private int numberPeople;
	
	@Column(name = "bt_note")
	private String note;
	
//	@ManyToOne
//	@JoinColumn(name = "fd_id")
//	private Food food;
	
	@ManyToOne
	@JoinColumn(name = "us_id")
	private User user;
}
