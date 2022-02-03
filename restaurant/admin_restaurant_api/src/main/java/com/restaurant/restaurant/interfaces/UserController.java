package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.UserService;
import com.restaurant.restaurant.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) throws URISyntaxException {
        user = userService.createUser(user.getEmail(), user.getName());
        URI location = new URI("/users/"+user.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/users/{userId}")
    public String updateUser(@PathVariable("userId") long userId, @RequestBody User user) throws URISyntaxException {
        user = User.builder().id(userId).name(user.getName())
                .email(user.getEmail()).level(user.getLevel()).build();
        userService.updateUser(user);
        return "{}";
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable("userId") long userId) throws URISyntaxException {
        userService.deleteUser(userId);
    }

}
