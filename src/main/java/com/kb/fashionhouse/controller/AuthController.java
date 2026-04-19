package com.kb.fashionhouse.controller;

import com.kb.fashionhouse.model.User;
import com.kb.fashionhouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthRestController {

    @Autowired
    private UserService userService;

    // =========================
    // REGISTER API
    // =========================
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if ((user.getEmail() == null || user.getEmail().isEmpty()) &&
            (user.getMobile() == null || user.getMobile().isEmpty())) {

            throw new RuntimeException("Email or Mobile is required");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        userService.registerUser(user);

        return "User registered successfully";
    }

    // =========================
    // LOGIN API (you must implement this in service)
    // =========================
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        boolean isValid = userService.login(user.getEmail(), user.getPassword());

        if (!isValid) {
            throw new RuntimeException("Invalid credentials");
        }

        return "Login successful";
    }
}
