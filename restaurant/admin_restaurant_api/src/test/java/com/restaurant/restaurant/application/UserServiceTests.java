package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.Restaurant;
import com.restaurant.restaurant.domain.User;
import com.restaurant.restaurant.domain.UserRepository;
import com.restaurant.restaurant.interfaces.RestaurantNotFoundException;
import com.restaurant.restaurant.interfaces.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
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
    public void createUser(){
        String email = "admin@example.com";
        String name  = "kjb";
        User user = User.builder().email(email).name(name).build();

        when(userRepository.save(user)).thenReturn(user);
        user = userService.createUser(email, name);

        assertThat(user.getEmail(), is(email));
    }

    @Test
    public void updateUser(){
        String email = "admin@example.com";
        String name  = "kjb";
        User user = User.builder().id(1004L).email(email).name(name).level(3L).build();

        User updatingUser = User.builder().id(1004L).email(email).name("kej").level(3L).build();
        when(userRepository.findById(1004L)).thenReturn(java.util.Optional.ofNullable(user));
        when(userRepository.save(updatingUser)).thenReturn(updatingUser);
        updatingUser = userService.updateUser(updatingUser);

        assert user != null;
        assertThat(user.getName(),is("kjb"));
        assertThat(updatingUser.getName(),is("kej"));
    }

    @Test
    public void deleteUser(){
        String email = "admin@example.com";
        String name  = "kjb";
        User user = User.builder().id(1004L).email(email).name(name).level(3L).build();
        when(userRepository.findById(1004L)).thenReturn(java.util.Optional.ofNullable(user));

        user = userService.deleteUser(1004L);

        assert user != null;
        assertThat(user.isActive(),is(false));
    }

    @Test
    public void userNotFound(){
        assertThrows(UserNotFoundException.class, () -> {
            User user = User.builder().build();
            userService.updateUser(user);
        });
    }
}