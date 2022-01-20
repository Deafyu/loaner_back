package com.example.loaner_back.repository;

import com.example.loaner_back.entity.ApplicationEntity;
import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.utils.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity,Long> {
    public List<ApplicationEntity> findAllByType(Type type);
}
