package com.opgave.aflevering.controllers;

import com.opgave.aflevering.models.User;
import com.opgave.aflevering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/verify")
    public String verify(User user) {
        if (userService.correctDetails(user)) {
            return "index";
        }
        return "login";
    }

    @RequestMapping("/registerVerify")
    public String registerVerify(User user) {
        System.out.println(user);
        if (!userService.isPresent(user.getUsername())) {
            userService.addNewUser(user);
            return "success";
        }
        return "register";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
