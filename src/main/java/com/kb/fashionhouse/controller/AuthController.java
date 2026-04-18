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
    // REGISTER USER (FIXED)
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
            // ✅ call updated service method
            userService.registerUser(user);

            return "redirect:/login?success";

        } catch (RuntimeException e) {
            // ✅ show proper error message in UI
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // =========================
    // FORGOT PASSWORD PAGE
    // =========================
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    // =========================
    // SEND RESET LINK
    // =========================
    @PostMapping("/forgot-password")
    public String sendResetLink(@RequestParam String email, Model model) {

        try {
            userService.sendResetPasswordEmail(email);
            model.addAttribute("message", "Reset link sent to your email");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "forgot-password";
    }

    // =========================
    // RESET PASSWORD PAGE
    // =========================
    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    // =========================
    // RESET PASSWORD ACTION
    // =========================
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password,
                                Model model) {

        try {
            userService.resetPassword(token, password);
            return "redirect:/login?resetSuccess";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("token", token);
            return "reset-password";
        }
    }
}
