package com.intern.restaurant.service;

import java.util.List;

import com.intern.restaurant.dto.BookTableDTO;
import com.intern.restaurant.model.BookTable;

public interface BookTableService {
	
	public BookTable createBookTable(BookTable bookTable);
	public List<BookTableDTO> listBookTableDTO();
	public List<BookTable> listBookTable();
}
