package com.intern.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.restaurant.model.BookTable;
import com.intern.restaurant.service.BookTableService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/book-table")
@CrossOrigin("*")
public class BookTableController {

	@Autowired
	private BookTableService bookTableService;
	
	@PostMapping("/create/")
	public ResponseEntity<?> createBookTable(@RequestBody BookTable bookTable) {
		return ResponseEntity.ok(bookTableService.createBookTable(bookTable));
	}
	
	@GetMapping("/list/")
	public ResponseEntity<?> listBookTable() {
		return ResponseEntity.ok(bookTableService.listBookTable());
	}

		@GetMapping("/by/date")
	public ResponseEntity<?> getBookTableByDate(@RequestParam("date") String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime ldt = LocalDateTime.parse(date,dtf);
		return ResponseEntity.ok(bookTableService.getBookTableByBookTableDate(ldt));
	}


	@GetMapping("/by/{id}")
	public BookTable getBookTableById(@PathVariable("id") int id) {
		return bookTableService.getBookTableByBookTableId(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBookTableById(@PathVariable("id") Integer id, @RequestBody BookTable updateBookTable) {
		return ResponseEntity.ok(bookTableService.updateByBookTableId(id,updateBookTable));
	}

	@DeleteMapping("/delete/{id}")
	public void deleteBookTableById(@PathVariable("id") int id) {
		bookTableService.deleteByBookTableId(id);
	}
}
