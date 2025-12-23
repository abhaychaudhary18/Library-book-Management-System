package com.abhay.calculator.web.repository;

import com.abhay.calculator.web.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByCategoryContainingIgnoreCase(String category);

    List<Book> findByBookIdContainingIgnoreCase(String bookId);

    Optional<Book> findByBookId(String bookId);

    List<Book> findByIssuedTo(String issuedTo);
}


