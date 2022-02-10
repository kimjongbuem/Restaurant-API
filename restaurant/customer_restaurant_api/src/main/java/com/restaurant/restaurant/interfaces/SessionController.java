package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.UserService;
import com.restaurant.restaurant.dto.RequestSessionDto;
import com.restaurant.restaurant.dto.ResponseSessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @PostMapping("/session")
    public ResponseEntity<?> login(@RequestBody RequestSessionDto resource) throws URISyntaxException {

        ResponseSessionDto accessToken = ResponseSessionDto.builder().accessToken(
                userService.authenticate(resource.getEmail(), resource.getPassword()).getAccessToken()
        ).build();
        String uri ="/session";
        return ResponseEntity.created(new URI(uri)).body(accessToken);
    }
}
