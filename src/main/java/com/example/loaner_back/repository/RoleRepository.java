package com.example.loaner_back.repository;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    <Optional>RoleEntity findByName(String name);

}
