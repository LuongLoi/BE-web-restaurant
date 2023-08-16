package com.intern.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.restaurant.dto.BookTableDTO;
import com.intern.restaurant.exception.BookTableException;
import com.intern.restaurant.mapper.BookTableMapper;
import com.intern.restaurant.model.BookTable;
import com.intern.restaurant.model.User;
import com.intern.restaurant.repository.BookTableRepository;

@Service
public class BookTableServiceImpl implements BookTableService{

	@Autowired
	private BookTableRepository bookTableRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public BookTable createBookTable(BookTable bookTable) {
		// TODO Auto-generated method stub
		int us_id = userService.getCurrentUserLogin();
		bookTable.setUser(new User(us_id));
		return bookTableRepository.save(bookTable);
	}

	@Override
	public List<BookTableDTO> listBookTableDTO() {
		// TODO Auto-generated method stub
		List<BookTableDTO> listDto = new ArrayList<>();
		for (BookTable bookTable: bookTableRepository.findAll()) {
			listDto.add(BookTableMapper.toBookTableDTO(bookTable));
		}
		if (listDto.size() == 0)
			throw new BookTableException("Book-Table is not found");
		return listDto;
	}

	@Override
	public List<BookTable> listBookTable() {
		// TODO Auto-generated method stub
		List<BookTable> listBooking = bookTableRepository.findAll();
		if (listBooking.size() == 0)
			throw new BookTableException("List Booking is empty");
		return listBooking;
	}

}
