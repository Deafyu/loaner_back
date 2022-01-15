package com.example.loaner_back.controllers;

import com.example.loaner_back.entity.UserEntity;
import com.example.loaner_back.service.UserService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FieldDefaults(makeFinal = true)
@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/id={userId}")
    @PreAuthorize("hasRole('ADMIN_ROLE') or hasRole('USER_ROLE')")
    public Optional<UserEntity> getUser(@PathVariable long userId) {
        return userService.getSingleUserById(userId);
    }

    @PreAuthorize("hasRole('ADMIN_ROLE') or hasRole('USER_ROLE')")
    @DeleteMapping(value = "/users/id={userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUserById(userId);
    }

}
