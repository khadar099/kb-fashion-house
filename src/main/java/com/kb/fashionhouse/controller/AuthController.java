package com.kb.fashionhouse.controller;

import com.kb.fashionhouse.model.User;
import com.kb.fashionhouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // =========================
    // LOGIN PAGE
    // =========================
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // =========================
    // REGISTER PAGE
    // =========================
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // =========================
    // REGISTER USER (NO OTP)
    // =========================
    @PostMapping("/register")
public String registerUser(@ModelAttribute User user, Model model) {

    // validation: email OR mobile must be present
    if ((user.getEmail() == null || user.getEmail().isEmpty()) &&
        (user.getMobile() == null || user.getMobile().isEmpty())) {

        model.addAttribute("error", "Email or Mobile is required");
        return "register";
    }

    // password check
    if (user.getPassword() == null || user.getPassword().isEmpty()) {
        model.addAttribute("error", "Password is required");
        return "register";
    }

    try {
        // ✅ CALL UPDATED METHOD
        userService.registerUser(user);

        return "redirect:/login?success";

    } catch (RuntimeException e) {
        // ✅ SHOW ERROR IN UI
        model.addAttribute("error", e.getMessage());
        return "register";
    }
}

        // save user
        userService.saveUser(user);

        return "redirect:/login?success";
    }
}
