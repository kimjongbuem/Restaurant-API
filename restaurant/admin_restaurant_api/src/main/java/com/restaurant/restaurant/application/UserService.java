package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.User;
import com.restaurant.restaurant.domain.UserRepository;
import com.restaurant.restaurant.interfaces.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(String email, String name) {
        User user = User.builder().email(email).level(1L).name(name).build();
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User updateUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(()-> new UserNotFoundException(user.getId()));
        return userRepository.save(user);
    }

    @Transactional
    public User deleteUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        user.setLevel(0);
        return user;
    }
}
