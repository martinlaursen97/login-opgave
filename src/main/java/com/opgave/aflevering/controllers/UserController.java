package com.opgave.aflevering.controllers;

import com.opgave.aflevering.models.User;
import com.opgave.aflevering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        userService.addNewUser(new User("martin", "123321"));
        return "login";
    }
}
