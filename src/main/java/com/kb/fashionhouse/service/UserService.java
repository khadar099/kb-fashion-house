package com.kb.fashionhouse.service;

import com.kb.fashionhouse.model.User;

public interface UserService {

    // =========================
    // REGISTER USER
    // =========================
    void registerUser(User user);

    // =========================
    // FIND USER BY EMAIL
    // =========================
    User findByEmail(String email);

    // =========================
    // LOGIN VALIDATION (IMPORTANT)
    // =========================
    boolean login(String email, String password);

    // =========================
    // FORGOT PASSWORD
    // =========================
    void sendResetPasswordEmail(String email);

    // =========================
    // RESET PASSWORD
    // =========================
    void resetPassword(String token, String newPassword);
}
