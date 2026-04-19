package com.kb.fashionhouse.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeRestController {

    // =========================
    // HOME API
    // =========================
    @GetMapping
    public String home() {
        return "Welcome to Fashion House API";
    }
}
