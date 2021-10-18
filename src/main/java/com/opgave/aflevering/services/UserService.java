package com.opgave.aflevering.services;

import com.opgave.aflevering.models.User;
import com.opgave.aflevering.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(User user) {
        if (!isPresent(user.getUsername())) {
            userRepository.addNewUser(user);
        }
    }

    public boolean isPresent(String username) {
        return userRepository.isPresent(username);
    }

    public boolean correctDetails(User user) {
        return userRepository.correctDetails(user);
    }
}
