package com.intern.restaurant.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		bookTable.setDatetime(LocalDateTime.now());
		return bookTableRepository.save(bookTable);
	}

	@Override
	public List<BookTableDTO> listBookTable() {
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
	public BookTable getBookTableByBookTableId(Integer btId) throws BookTableException {
		return bookTableRepository.findById(btId).orElseThrow(()-> new BookTableException("No order exists with given OrderId "+ btId));


	}

	@Override
	public List<BookTableDTO> getBookTableByBookTableDate(LocalDateTime dataTime) {
		List<BookTableDTO> listDto = new ArrayList<>();

		for (BookTable bookTable: bookTableRepository.findByDatetime(dataTime)) {
			listDto.add(BookTableMapper.toBookTableDTO(bookTable));
		}
		if (listDto.size() == 0)
			throw new BookTableException("Book-Table-Date is not found");
		return listDto;
	}

	@Override
	public boolean updateByBookTableId(Integer btId, BookTable updateBookTable) {

		Optional<BookTable> optionalBookTable = bookTableRepository.findById(btId);
		if (optionalBookTable.isPresent()) {
			if (!bookTableRepository.findById(updateBookTable.getBt_id()).isPresent())
				return false;
			else {
				BookTable category = optionalBookTable.get();
				category.setNote(updateBookTable.getNote());
				category.setUser(updateBookTable.getUser());
				category.setDatetime(updateBookTable.getDatetime());
				category.setFood(updateBookTable.getFood());
				category.setNumberPeople(updateBookTable.getNumberPeople());
				bookTableRepository.save(category);
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteByBookTableId(int id) {
		Optional<BookTable> optionalBookTable = bookTableRepository.findById(id);
		if (!optionalBookTable.isPresent())
			throw new BookTableException("Book Table not found");
		bookTableRepository.deleteById(id);
	}

}
