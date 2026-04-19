@Override
public boolean login(String email, String password) {

    User user = userRepository.findByEmail(email).orElse(null);

    if (user == null) {
        return false;
    }

    // ✅ correct way to compare encrypted password
    return passwordEncoder.matches(password, user.getPassword());
}
