package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.RestaurantService;
import com.restaurant.restaurant.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurant(@PathVariable("id") long id){
        return restaurantService.getRestaurant(id);
    }

}
