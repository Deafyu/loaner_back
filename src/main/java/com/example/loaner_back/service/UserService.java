package com.example.loaner_back.service;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.entity.UserEntity;
import com.example.loaner_back.repository.LoanRepository;
import com.example.loaner_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    LoanRepository loanRepository;
    UserRepository userRepository;

    @Autowired
    public UserService(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getSingleUserById(long id) {
        return userRepository.findById(id);
    }

    public long getSingleUserByNameAndReturnId(String name) {
        UserEntity user = userRepository.findByName(name).orElseThrow(RuntimeException::new);
        return user.getId();
    }

    public void createUser(UserEntity UserEntity) {
        userRepository.save(UserEntity);
    }

    public void deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
