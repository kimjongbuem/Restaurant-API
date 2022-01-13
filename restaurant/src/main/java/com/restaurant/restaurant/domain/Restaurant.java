package com.restaurant.restaurant.domain;

public class Restaurant {
    private final long id;
    private final String name;
    private final String address;

    public Restaurant(long id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
