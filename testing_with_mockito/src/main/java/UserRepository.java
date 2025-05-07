

public interface UserRepository {
    User findByEmail(String email) throws RuntimeException;
}
