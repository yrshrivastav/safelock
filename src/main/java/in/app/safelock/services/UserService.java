package in.app.safelock.services;

import java.util.List;
import java.util.Optional;

import in.app.safelock.entities.User;

public interface UserService {
    
    User saveUser(User user);

    Optional<User> getUserById(long userId);

    Optional<User> updateUser(User user);

    void deleteUser(long id);

    boolean isUserExist(long userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

}
