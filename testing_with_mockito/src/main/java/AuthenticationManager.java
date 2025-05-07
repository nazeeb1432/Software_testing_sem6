public class AuthenticationManager {

    private final UserRepository userRepository;
    private final HashLibrary hashLibrary;

    public AuthenticationManager(UserRepository userRepository, HashLibrary hashLibrary) {
        this.userRepository = userRepository;
        this.hashLibrary = hashLibrary;
    }

    public User login(String email, String password) {
        // Find user by email
        User user = userRepository.findByEmail(email);

        // Verify password
        boolean isPasswordValid = hashLibrary.verifyPassword(password, user.getPassword());
        if (!isPasswordValid) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }


}
