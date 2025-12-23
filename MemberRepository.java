package com.abhay.calculator.web.repository;

import com.abhay.calculator.web.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}


