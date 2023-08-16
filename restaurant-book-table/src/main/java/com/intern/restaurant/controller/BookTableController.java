package com.intern.restaurant.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/create/")
	public String showBookingForm(Model model) {
		model.addAttribute("booktable", new BookTable());
		List<String> timeOptions = Arrays.asList("08:00:00", "10:00:00", "12:00:00", "14:00:00", "16:00:00", "18:00:00", "20:00:00", "21:00:00");
	    model.addAttribute("timeOptions", timeOptions);
	    
		
		return "booking_form_add";
	}
	
	@PostMapping("/create/save/")
	public String bookingCreate(@RequestParam("selectedTime") String selectedTime, BookTable booktable) {
		LocalDate currentDate = LocalDate.now();
	    LocalTime time = LocalTime.parse(selectedTime);
	    LocalDateTime dateTime = LocalDateTime.of(currentDate, time);
		
        booktable.setDatetime(dateTime);
		bookTableService.createBookTable(booktable);
		return "redirect:/home/";
	}
}
