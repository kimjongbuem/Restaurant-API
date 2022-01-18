package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.MenuItem;
import com.restaurant.restaurant.domain.MenuItemRepository;
import com.restaurant.restaurant.domain.Restaurant;
import com.restaurant.restaurant.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private final MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

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
