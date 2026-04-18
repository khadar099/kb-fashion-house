package com.kb.fashionhouse.repository;

import com.kb.fashionhouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    // ✅ Check if email already exists
    boolean existsByEmail(String email);

    // ✅ Check if mobile already exists
    boolean existsByMobile(String mobile);

    // 🔐 For forgot password
    Optional<User> findByResetToken(String resetToken);
}
