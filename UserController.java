package com.abhay.calculator.web.controller;

import com.abhay.calculator.web.model.Book;
import com.abhay.calculator.web.repository.BookRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final BookRepository bookRepository;

    public UserController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<Book> issuedBooks = bookRepository.findAll().stream()
                .filter(b -> !b.isAvailable() && username.equals(b.getIssuedTo()))
                .toList();

        LocalDate today = LocalDate.now();
        model.addAttribute("issuedBooks", issuedBooks);
        model.addAttribute("today", today);
        return "user-dashboard";
    }

    @GetMapping("/books")
    public String browseBooks(@RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "filter", required = false, defaultValue = "title") String filter,
                              Model model) {
        List<Book> books;
        if (query != null && !query.isBlank()) {
            switch (filter) {
                case "bookId":
                    books = bookRepository.findByBookIdContainingIgnoreCase(query);
                    break;
                case "author":
                    books = bookRepository.findByAuthorContainingIgnoreCase(query);
                    break;
                case "category":
                    books = bookRepository.findByCategoryContainingIgnoreCase(query);
                    break;
                default:
                    books = bookRepository.findByTitleContainingIgnoreCase(query);
            }
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("query", query);
        model.addAttribute("filter", filter);
        return "user-books";
    }
}


