package com.kb.fashionhouse.service;

import com.kb.fashionhouse.model.User;
import com.kb.fashionhouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ REGISTER USER (FIXED)
    @Override
    public void registerUser(User user) {

        // 🔴 Check duplicate email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // 🔴 Check duplicate mobile
        if (userRepository.existsByMobile(user.getMobile())) {
            throw new RuntimeException("Mobile number already registered");
        }

        // ✅ Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    // ✅ FIND BY EMAIL (for login)
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // 🔐 SEND RESET PASSWORD EMAIL
    @Override
    public void sendResetPasswordEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);
        user.setTokenExpiry(LocalDateTime.now().plusMinutes(30));

        userRepository.save(user);

        // 👉 Replace with your frontend URL
        String resetLink = "http://localhost:8080/reset-password?token=" + token;

        // ⚠️ For now just print (we’ll connect email later)
        System.out.println("Reset Link: " + resetLink);
    }

    // 🔐 RESET PASSWORD
    @Override
    public void resetPassword(String token, String newPassword) {

        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setTokenExpiry(null);

        userRepository.save(user);
    }
}
