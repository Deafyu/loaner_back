package com.example.loaner_back.repository;

import com.example.loaner_back.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity,Long> {
}
