import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationManagerTest {

    // Mocked dependencies
    @Mock
    private UserRepository userRepository;

    @Mock
    private HashLibrary hashLibrary;
    private String validEmail;
    private String invalidEmail;
    private String validPassword;
    private String invalidPassword;
    private String hashedPassword;

    // Class under test
    @InjectMocks
    private AuthenticationManager authenticationManager;
    private User user;
    @BeforeEach
    void setUp() throws UserNotFoundException {
        MockitoAnnotations.openMocks(this);
        validEmail = "test@example.com";
        invalidEmail = "nonexistent@example.com";
        validPassword = "password123";
        invalidPassword = "wrongPassword";
        hashedPassword = "hashedPassword123";
        user = new User(validEmail, hashedPassword);



        // Default mock behavior (problematic)
        when(userRepository.findByEmail(validEmail)).thenReturn(user);
        when(userRepository.findByEmail(invalidEmail)).thenThrow(new UserNotFoundException("User not found"));
        when(hashLibrary.matches(validPassword, hashedPassword)).thenReturn(true);
        when(hashLibrary.matches(invalidPassword, hashedPassword)).thenReturn(false);
    }

    @Test
    void testLoginSuccess() throws UserNotFoundException, InvalidCredentialsException {
        User result = authenticationManager.login(validEmail, validPassword);
        assertNotNull(result);
        assertEquals(validEmail, result.getEmail());
        verify(userRepository).findByEmail(validEmail);
        verify(hashLibrary).matches(validPassword, hashedPassword);
    }

    @Test
    void testLoginUserNotFound() {
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            authenticationManager.login(invalidEmail, validPassword);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findByEmail(invalidEmail);
        verifyNoInteractions(hashLibrary);
    }

    @Test
    void testLoginInvalidPassword() {
        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> {
            authenticationManager.login(validEmail, invalidPassword);
        });
        assertEquals("Invalid password", exception.getMessage());
        verify(userRepository).findByEmail(validEmail);
        verify(hashLibrary).matches(invalidPassword, hashedPassword);
    }
}