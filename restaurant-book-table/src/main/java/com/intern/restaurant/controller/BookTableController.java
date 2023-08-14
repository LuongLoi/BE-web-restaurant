package com.intern.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.restaurant.model.BookTable;
import com.intern.restaurant.service.BookTableService;

@Controller
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookTableController {

	@Autowired
	private BookTableService bookTableService;
	
	@GetMapping("/list/")
	public String listBookTable(Model model) {
		List<BookTable> listBooking = bookTableService.listBookTable();
		model.addAttribute("listBooking", listBooking);
		return "booking_list";
	}
}
