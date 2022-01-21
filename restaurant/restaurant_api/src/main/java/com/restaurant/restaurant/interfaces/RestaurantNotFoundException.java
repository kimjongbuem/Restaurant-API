package com.restaurant.restaurant.interfaces;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(long id) {
        super("Not found Restaurant Id : " + id);
    }
}
