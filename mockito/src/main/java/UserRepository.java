public interface UserRepository {

    public User findByEmail(String email) throws UserNotFoundException;
}
