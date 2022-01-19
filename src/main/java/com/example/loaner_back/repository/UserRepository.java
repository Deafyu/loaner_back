package com.example.loaner_back.repository;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.entity.RoleEntity;
import com.example.loaner_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
//    Optional<UserEntity> findByFirstName(String name);

    Optional<UserEntity> findByEmail(String email);

    Optional<List<UserEntity>> findByRoles(RoleEntity role);
}
