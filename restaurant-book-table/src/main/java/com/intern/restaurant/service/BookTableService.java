package com.intern.restaurant.service;

import java.util.List;
import java.time.LocalDateTime;

import com.intern.restaurant.dto.BookTableDTO;
import com.intern.restaurant.model.BookTable;

public interface BookTableService {
	
	public BookTable createBookTable(BookTable bookTable);
	public List<BookTableDTO> listBookTable();

	public BookTable getBookTableByBookTableId(Integer btId);

	public List<BookTableDTO> getBookTableByBookTableDate(LocalDateTime dataTime);

	public boolean updateByBookTableId(Integer btId, BookTable updateBookTable);

	public void deleteByBookTableId(int id);
}
