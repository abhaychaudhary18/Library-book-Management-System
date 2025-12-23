package com.abhay.calculator.web.repository;

import com.abhay.calculator.web.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

    Optional<LibraryUser> findByUsername(String username);
}


