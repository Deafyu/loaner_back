package com.example.loaner_back.controllers;

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

    @RequestMapping(value = "/")
    public String home() {
        return "/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String showRegisterForm() {
        return "register";
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/register")
//    public String registerUser(@RequestBody UserRegisterDto userRegisterDto) {
//        userService.createUser(userRegisterDto);
//        return "success";
//    }
}
