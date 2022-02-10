package com.restaurant.restaurant.interfaces;

public class EmailInValidException extends RuntimeException{
    public EmailInValidException(){
        super("Email is not Existed!");
    }
}
