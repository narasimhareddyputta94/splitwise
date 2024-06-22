package com.example.splitwise.service;

import com.example.splitwise.Repositories.UserRepository;
import com.example.splitwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // No need for PasswordEncoder bean here

    public User registerUser(User user) {
        // Directly save the password without encoding
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticateUser(String email, String rawPassword) {
        User user = findByEmail(email);
        // Directly compare the raw passwords
        return user != null && rawPassword.equals(user.getPassword());
    }
}
