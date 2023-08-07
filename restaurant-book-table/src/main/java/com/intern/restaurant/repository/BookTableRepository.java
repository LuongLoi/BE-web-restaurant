package com.intern.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.restaurant.model.BookTable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookTableRepository extends JpaRepository<BookTable, Integer>{

  List<BookTable> findByDatetime(LocalDateTime dateTime);
}
