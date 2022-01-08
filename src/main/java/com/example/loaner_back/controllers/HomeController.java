package com.example.loaner_back.controllers;

import com.example.loaner_back.dto.UserDto;
import com.example.loaner_back.entity.UserEntity;
import com.example.loaner_back.exception.UserAlreadyExistException;
import com.example.loaner_back.service.UserService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@FieldDefaults(makeFinal = true)
@RestController
public class HomeController {
    UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/")
    public String home() {
        return "/";
    }

    @PostMapping(value = "/register")
    public void showRegisterForm(UserDto userEntity) throws UserAlreadyExistException {
        userService.registerNewUserAccount(userEntity);
    }

}
