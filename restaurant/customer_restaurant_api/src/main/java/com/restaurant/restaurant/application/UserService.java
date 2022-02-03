package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.User;
import com.restaurant.restaurant.domain.UserRepository;
import com.restaurant.restaurant.interfaces.UserExistedEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public User createUser(String email, String name, String password) throws UserExistedEmailException {

        if(userRepository.findByEmail(email).isPresent()){
            throw new UserExistedEmailException(email);
        }
        User user = User.builder()
                        .email(email).level(1L)
                        .name(name).password(new BCryptPasswordEncoder().encode(password))
                        .build();

        userRepository.save(user);
        return user;
    }
}
