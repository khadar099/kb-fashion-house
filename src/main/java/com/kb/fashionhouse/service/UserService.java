package com.kb.fashionhouse.service;

import com.kb.fashionhouse.model.User;

public interface UserService {
    void saveUser(User user);
    User findByUsername(String username);
}
