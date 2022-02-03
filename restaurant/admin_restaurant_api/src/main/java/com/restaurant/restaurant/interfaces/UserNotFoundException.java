package com.restaurant.restaurant.interfaces;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id) {
        super("Not found User Id : " + id);
    }
}
