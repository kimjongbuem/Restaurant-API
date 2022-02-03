package com.restaurant.restaurant.interfaces;

public class UserExistedEmailException extends Exception{
    public UserExistedEmailException(String email){
        super("Email is already existed : " + email);
    }
}
