package com.example.loaner_back.controllers;

import com.example.loaner_back.entity.UserEntity;
import com.example.loaner_back.service.UserService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@FieldDefaults(makeFinal = true)
@RestController
@Transactional
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/id={userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Optional<UserEntity> getUser(@PathVariable long userId) {
        return userService.getSingleUserById(userId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping(value = "/users/id={userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUserById(userId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping(value = "/lenders")
    public List<UserEntity> getAllLenders(@RequestBody String name) {
        return userService.getUsersByRole(name).orElse(null);
    }
}
