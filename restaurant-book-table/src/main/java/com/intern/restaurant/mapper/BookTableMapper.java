package com.intern.restaurant.mapper;

import com.intern.restaurant.dto.BookTableDTO;
import com.intern.restaurant.model.BookTable;

public class BookTableMapper {

	public static BookTableDTO toBookTableDTO(BookTable bookTable) {
		BookTableDTO bookTableDto = new BookTableDTO();
		bookTableDto.setBt_id(bookTable.getBt_id());
		bookTableDto.setDatetime(bookTable.getDatetime());
		bookTableDto.setNote(bookTable.getNote());
		bookTableDto.setNumberPeople(bookTable.getNumberPeople());
//		bookTableDto.setFd_id(bookTable.getFood().getFd_id());
		bookTableDto.setUs_id(bookTable.getUser().getUs_id());
		return bookTableDto;
	}
}
