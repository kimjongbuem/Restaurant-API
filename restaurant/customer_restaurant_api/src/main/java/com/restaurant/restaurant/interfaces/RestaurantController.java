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

    @GetMapping(value = "/restaurants")
    public List<Restaurant> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    @GetMapping(value = "/restaurants", params = {"region"})
    public List<Restaurant> getRestaurants(@RequestParam("region") String region){
        return restaurantService.getRestaurants(region);
    }

    @GetMapping(value = "/restaurants", params = {"categoryId"})
    public List<Restaurant> getRestaurants(@RequestParam("categoryId") long categoryId){
        return restaurantService.getRestaurants(categoryId);
    }

    @GetMapping(value = "/restaurants", params = {"region", "categoryId"})
    public List<Restaurant> getRestaurants(@RequestParam("region") String region, @RequestParam("categoryId") long categoryId){
        return restaurantService.getRestaurants(region, categoryId);
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurant(@PathVariable("id") long id){
        return restaurantService.getRestaurant(id);
    }

}
