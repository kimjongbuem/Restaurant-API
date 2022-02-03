package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.UserService;
import com.restaurant.restaurant.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void createUser() throws Exception {
        String email = "admin@example.com";
        String name  = "kjb";
        String password = "test";
        User user = User.builder().email(email).name(name).build();

        when(userService.createUser(email, name, password)).thenReturn(user);
        user = userService.createUser(email, name, password);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@example.com\",\"name\":\"kjb\", \"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) header().string("location", "/users/"+user.getId()))
                .andExpect(content().string(containsString("{}")));
    }
}