
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationManagerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HashLibrary hashLibrary;

    @InjectMocks
    private AuthenticationManager authenticationManager;

    // Test variables
    private String email;
    private String password;
    private String nonExistentEmail;
    private String nonExistentPassword;
    private String wrongPassword;
    private User user;

    @BeforeEach
    void setup() {
        // Initialize test variables
        email = "test@example.com";
        password = "password123";
        nonExistentEmail = "nonexistent@example.com";
        nonExistentPassword = "nonexistentPass";
        wrongPassword = "wrongPassword";
        user = new User(email, password);
    }

    @Test
    void testLogin_Success() {
        // Arrange
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(hashLibrary.verifyPassword(password, user.getPassword())).thenReturn(true);

        // Act
        User result = authenticationManager.login(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());
        verify(userRepository).findByEmail(email);
        verify(hashLibrary).verifyPassword(password, user.getPassword());
    }

    @Test
    void testLogin_UserNotFound_ThrowsException() {
        // Arrange
        when(userRepository.findByEmail(nonExistentEmail)).thenThrow(new RuntimeException("User not found"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authenticationManager.login(nonExistentEmail, nonExistentPassword);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findByEmail(nonExistentEmail);
        verifyNoInteractions(hashLibrary);
    }

    @Test
    void testLogin_InvalidPassword_ThrowsException() {
        // Arrange
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(hashLibrary.verifyPassword(wrongPassword, user.getPassword())).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authenticationManager.login(email, wrongPassword);
        });
        assertEquals("Invalid password", exception.getMessage());
        verify(userRepository).findByEmail(email);
        verify(hashLibrary).verifyPassword(wrongPassword, user.getPassword());
    }
}