package com.example.mycommunity.services;

import com.example.mycommunity.models.User;
import com.example.mycommunity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Find user by door number
    public Optional<User> findUserByDoorNo(String doorNo) {
        return userRepository.findByDoorNo(doorNo);
    }

    // Save user to the repository
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Check credentials during login
    public boolean checkCredentials(String doorNo, String password) {
        Optional<User> userOpt = findUserByDoorNo(doorNo);
        return userOpt.isPresent() && userOpt.get().getPassword().equals(password);
    }
}
