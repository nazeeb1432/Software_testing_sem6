public class AuthenticationManager {
    private final UserRepository userRepository;
    private final HashLibrary hashLibrary;

    public AuthenticationManager(UserRepository userRepository, HashLibrary hashLibrary) {
        this.userRepository = userRepository;
        this.hashLibrary = hashLibrary;
    }

    public User login(String email, String password) throws UserNotFoundException, InvalidCredentialsException {
        User user = userRepository.findByEmail(email);
        if (!hashLibrary.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }
        return user;
    }
}
