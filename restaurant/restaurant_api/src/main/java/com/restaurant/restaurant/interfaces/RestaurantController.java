package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.RestaurantService;
import com.restaurant.restaurant.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/restaurants")
    public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant resource) throws URISyntaxException {
        Restaurant restaurant = new Restaurant(resource.getName(), resource.getAddress());
        restaurantService.save(restaurant);
        URI location = new URI("/restaurant/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public void patchRestaurant(@PathVariable("id") long id, @RequestBody Restaurant resource){
        restaurantService.patchRestaurant(id, resource.getName(), resource.getAddress());
    }

}
