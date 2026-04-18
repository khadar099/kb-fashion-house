package com.kb.fashionhouse.service;

import com.kb.fashionhouse.model.User;

public interface UserService {

    // ✅ Register user
    void registerUser(User user);

    // ✅ Find by email (for login or validation)
    User findByEmail(String email);

    // 🔐 Forgot password
    void sendResetPasswordEmail(String email);

    void resetPassword(String token, String newPassword);
}
