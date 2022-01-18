package com.restaurant.restaurant.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String address;

    @Transient
    private List<MenuItem> menuItems;

    public Restaurant(){

    }

    public Restaurant(String name, String address){
        this.name = name;
        this.address = address;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
