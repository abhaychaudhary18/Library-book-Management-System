package com.abhay.calculator.web.controller;

import com.abhay.calculator.web.model.Book;
import com.abhay.calculator.web.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class CalculatorController {

    private final BookRepository bookRepository;

    public CalculatorController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String listBooks(@RequestParam(value = "q", required = false) String query,
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
        return "books";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping
    public String createBook(@ModelAttribute Book book) {
        book.setAvailable(true);
        book.setIssuedTo(null);
        book.setDueDate(null);
        bookRepository.save(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book formBook) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        existing.setBookId(formBook.getBookId());
        existing.setTitle(formBook.getTitle());
        existing.setAuthor(formBook.getAuthor());
        existing.setCategory(formBook.getCategory());
        bookRepository.save(existing);
        return "redirect:/admin/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/admin/books";
    }

    @PostMapping("/{id}/issue")
    public String issueBook(@PathVariable Long id,
                            @RequestParam("issuedTo") String issuedTo,
                            @RequestParam("dueDate") String dueDateStr) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        if (book.isAvailable()) {
            book.setAvailable(false);
            book.setIssuedTo(issuedTo);
            if (dueDateStr != null && !dueDateStr.isBlank()) {
                book.setDueDate(LocalDate.parse(dueDateStr));
            } else {
                book.setDueDate(null);
            }
            bookRepository.save(book);
        }
        return "redirect:/admin/books";
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        book.setAvailable(true);
        book.setIssuedTo(null);
        book.setDueDate(null);
        bookRepository.save(book);
        return "redirect:/admin/books";
    }
}

