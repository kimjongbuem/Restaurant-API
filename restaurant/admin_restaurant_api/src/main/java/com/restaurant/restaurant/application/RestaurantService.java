package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.*;
import com.restaurant.restaurant.interfaces.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(long id) {
        return restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException(id));
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    // @Transactional은 해당 어노테이션의 메서드 범위 안에서 엔티티가 바뀐다면 수정해준다.
    @Transactional
    public void patchRestaurant(long id, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        assert restaurant != null;
        restaurant.setName(name);
        restaurant.setAddress(address);
    }
}
