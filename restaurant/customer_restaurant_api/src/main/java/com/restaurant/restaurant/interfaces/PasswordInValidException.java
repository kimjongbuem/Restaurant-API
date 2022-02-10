package com.restaurant.restaurant.interfaces;

public class PasswordInValidException extends RuntimeException {
    public PasswordInValidException(){
        super("Password is Wrong!");
    }
}
