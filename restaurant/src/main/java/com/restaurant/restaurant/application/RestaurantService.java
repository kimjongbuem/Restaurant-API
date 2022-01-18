package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.MenuItem;
import com.restaurant.restaurant.domain.MenuItemRepository;
import com.restaurant.restaurant.domain.Restaurant;
import com.restaurant.restaurant.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(long id) {
        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        assert restaurant != null;
        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}
