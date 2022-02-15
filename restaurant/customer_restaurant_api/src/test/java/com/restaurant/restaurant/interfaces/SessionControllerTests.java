package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.UserService;
import com.restaurant.restaurant.domain.User;
import com.restaurant.restaurant.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SessionController.class)
class SessionControllerTests {
    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private MockMvc mvc;

    @Test
    public void authenticateWithValidData() throws Exception {
        long id = 1004L;
        String name = "kjb";

        User mockUser = User.builder().name(name).id(id).build();

        given(userService.authenticate("test@example.com", "test")).willReturn(mockUser);
        given(jwtUtils.getToken(id, name)).willReturn("header.payload.signature");
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}")));

        verify(userService).authenticate("test@example.com", "test");
    }

    @Test
    public void authenticateWithInValidEmail() throws Exception {
        given(userService.authenticate("x@example.com", "test")).willThrow(EmailInValidException.class);
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x@example.com\", \"password\":\"test\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void authenticateWithInValidPassword() throws Exception {
        given(userService.authenticate("test@example.com", "x")).willThrow(PasswordInValidException.class);
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"x\"}"))
                .andExpect(status().isBadRequest());
    }
}