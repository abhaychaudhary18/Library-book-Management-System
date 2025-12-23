package com.abhay.calculator.web.controller;

import com.abhay.calculator.web.model.Book;
import com.abhay.calculator.web.model.LibraryUser;
import com.abhay.calculator.web.repository.BookRepository;
import com.abhay.calculator.web.repository.LibraryUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookRepository bookRepository;
    private final LibraryUserRepository userRepository;

    public AdminController(BookRepository bookRepository, LibraryUserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalBooks = bookRepository.count();
        long availableBooks = bookRepository.findAll().stream().filter(Book::isAvailable).count();
        long issuedBooks = totalBooks - availableBooks;

        long totalUsers = userRepository.count();

        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("issuedBooks", issuedBooks);
        model.addAttribute("totalUsers", totalUsers);

        return "admin-dashboard";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<LibraryUser> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin-users";
    }

    @PostMapping("/users/{id}/toggle")
    public String toggleUser(@PathVariable Long id) {
        LibraryUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        user.setActive(!user.isActive());
        userRepository.save(user);
        return "redirect:/admin/users";
    }
}


