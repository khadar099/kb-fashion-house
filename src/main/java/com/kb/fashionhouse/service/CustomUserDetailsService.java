@Override
public UserDetails loadUserByUsername(String input) {

    User user = userRepository.findByUsername(input);

    if (user == null) {
        user = userRepository.findByEmail(input);
    }

    if (user == null) {
        user = userRepository.findByMobile(input);
    }

    if (user == null) {
        throw new UsernameNotFoundException("User not found");
    }

    return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            new ArrayList<>()
    );
}
