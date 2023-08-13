package com.intern.restaurant.model;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "food")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fd_id;
	
	@Column(name = "fd_name")
	private String name;
	
	
	@Column(name = "fd_thumbnail")
	private String thumbnail;
	
	@Column(name = "fd_price")
	private int price;
	
	@NotNull(message = "Enter description")
	@Column(name = "fd_description")
	private String description;
	
	@Column(name = "fd_youtube_link")
	private String youtube_link;
	
	@ManyToOne
	@JoinColumn(name = "cf_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "us_id")
	private User user;
	
	public Food(int fd_id) {
		this.fd_id = fd_id;
	}
}
