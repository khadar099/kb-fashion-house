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

    // ✅ LOGIN PAGE
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ✅ REGISTER PAGE
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        // optional: basic validation
        if (user.getUsername() == null || user.getPassword() == null) {
            model.addAttribute("error", "Username and Password required");
            return "register";
        }

        userService.saveUser(user);

        return "redirect:/login?success";
    }
}
