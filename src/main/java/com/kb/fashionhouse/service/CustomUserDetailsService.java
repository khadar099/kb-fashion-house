package com.kb.fashionhouse.service;

import com.kb.fashionhouse.model.User;
import com.kb.fashionhouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        // Try email first
        User user = userRepository.findByEmail(input).orElse(null);

        // If not found, try mobile
        if (user == null) {
            user = userRepository.findByMobile(input).orElse(null);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email or mobile: " + input);
        }

        // Use email as internal identifier (safe choice)
        String loginId = (user.getEmail() != null) ? user.getEmail() : user.getMobile();

        return new org.springframework.security.core.userdetails.User(
                loginId,
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
