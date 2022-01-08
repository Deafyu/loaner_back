package com.example.loaner_back.repository;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByName(String name);

    UserEntity findByEmail(String email);
}
