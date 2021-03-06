package com.opgave.aflevering.controllers;

import com.opgave.aflevering.models.User;
import com.opgave.aflevering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/loginVerify")
    public String loginVerify(User user, Model model) {
        if (userService.correctDetails(user)) {
            model.addAttribute("user", user);
            model.addAttribute("users", userService.fetchAllUsers());
            return "index";
        }
        return "login";
    }

    @PostMapping("/registerVerify")
    public String registerVerify(User user) {
        if (!userService.usernameTaken(user)) {
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
