package in.app.safelock.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.app.safelock.entities.User;
import in.app.safelock.repository.UserRepo;
import in.app.safelock.services.UserService;
import in.app.safelock.support.ResourceNotFoundException;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Logger to check for logs
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        
        //password encrypted
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set the profile picture to default 
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhoneNo(user.getPhoneNo());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());
        // save the user in database
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(long id) {
        User user2 = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(long userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
    

}
