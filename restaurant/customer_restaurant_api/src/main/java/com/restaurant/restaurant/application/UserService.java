package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.User;
import com.restaurant.restaurant.domain.UserRepository;
import com.restaurant.restaurant.interfaces.EmailInValidException;
import com.restaurant.restaurant.interfaces.PasswordInValidException;
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

    @Autowired
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
                        .name(name).password(passwordEncoder.encode(password))
                        .build();

        userRepository.save(user);
        return user;
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(EmailInValidException::new);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordInValidException();
        }
        return user;
    }
}
