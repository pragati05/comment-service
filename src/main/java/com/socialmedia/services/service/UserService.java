package com.socialmedia.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmedia.services.entity.User;
import com.socialmedia.services.repository.UserRepository;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId ) {
        return userRepository.findById(userId).orElse(null);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

}
