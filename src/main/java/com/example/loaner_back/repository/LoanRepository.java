package com.example.loaner_back.repository;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LoanRepository extends JpaRepository<LoanEntity,Long> {

    Optional<LoanEntity> findByName(String name);

    Optional<List<LoanEntity>> findByLoanCreator(UserEntity loanCreator);

}
