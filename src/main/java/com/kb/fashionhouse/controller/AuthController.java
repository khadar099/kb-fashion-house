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

    // TEMP STORAGE (for OTP - simple version)
    private String generatedOtp;
    private String tempMobile;

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
    // SEND OTP (NEW)
    // =========================
    @GetMapping("/send-otp")
    @ResponseBody
    public String sendOtp(@RequestParam String mobile) {

        // generate simple OTP
        generatedOtp = String.valueOf((int)(Math.random() * 9000) + 1000);
        tempMobile = mobile;

        // NOTE: integrate SMS API here later (Twilio / AWS SNS)

        System.out.println("OTP for " + mobile + " is: " + generatedOtp);

        return "OTP sent to " + mobile;
    }

    // =========================
    // REGISTER USER WITH OTP
    // =========================
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               @RequestParam String otp,
                               Model model) {

        // validate OTP
        if (generatedOtp == null || !generatedOtp.equals(otp)) {
            model.addAttribute("error", "Invalid OTP");
            return "register";
        }

        // validate mobile match
        if (!user.getMobile().equals(tempMobile)) {
            model.addAttribute("error", "Mobile mismatch");
            return "register";
        }

        // save user
        userService.saveUser(user);

        // clear OTP after use
        generatedOtp = null;
        tempMobile = null;

        return "redirect:/login?success";
    }
}
