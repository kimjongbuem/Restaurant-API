package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.User;
import com.restaurant.restaurant.domain.UserRepository;
import com.restaurant.restaurant.interfaces.UserExistedEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTests {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers(){
        String email = "admin@example.com";

        List<User> userList = new ArrayList<>();
        userList.add(User.builder().email(email).build());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> getUserData = userService.getUsers();
        verify(userRepository).findAll();
        assertThat(getUserData.get(0).getEmail(), is(email));
    }

    @Test
    public void createUser() throws UserExistedEmailException {
        String email = "admin@example.com";
        String name  = "kjb";
        String password = "test";
        User user = User.builder().email(email).name(name).password(password).build();

        when(userRepository.save(user)).thenReturn(user);
        user = userService.createUser(email, name, password);

        assertThat(user.getEmail(), is(email));
    }

    @Test
    public void createUserWithAlreadyEmail(){

        String email = "admin@example.com";
        String name  = "kjb";
        String password = "test";
        User user = User.builder().email(email).name(name).build();

        assertThrows(UserExistedEmailException.class, ()->{
            when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.ofNullable(user));
            userService.createUser(email, name, password);
        });
    }

}