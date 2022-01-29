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

    @Autowired
    private final MenuItemRepository menuItemRepository;

    @Autowired
    private final ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurants(String region) {
        return restaurantRepository.findAllByAddressContaining(region);
    }

    public List<Restaurant> getRestaurants(long categoryId) {
        return restaurantRepository.findAllByCategoryId(categoryId);
    }

    public List<Restaurant> getRestaurants(String region, long categoryId) {
        return restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);
    }

    public Restaurant getRestaurant(long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> reviews = reviewRepository.findByRestaurantId(id);
        restaurant.setReviews(reviews);

        return restaurant;
    }

}
