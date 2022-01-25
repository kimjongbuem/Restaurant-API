package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.*;
import com.restaurant.restaurant.interfaces.RestaurantNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantServiceTests {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantService(restaurantRepository);
        testInitGetRestaurants();
        testInitGetRestaurant();
    }

    public void testInitGetRestaurants(){
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(Restaurant.builder().name("JOKER HOUSE").address("Seoul").build());
        restaurantList.add(Restaurant.builder().name("NUKER HOUSE").address("Seoul").build());
        when(restaurantRepository.findAll()).thenReturn(restaurantList);
    }

    public void testInitGetRestaurant(){
        Restaurant restaurant = Restaurant.builder().name("JOKER HOUSE").address("Seoul").build();
        when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        assert restaurants != null;
        assertThat(restaurants.size(), is(2));
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assert restaurant != null;
        assertThat(restaurant.getName(), is("JOKER HOUSE"));
    }

    @Test
    public void getInValidRestaurant(){
        assertThrows(RestaurantNotFoundException.class, () -> {
            Restaurant restaurant = restaurantService.getRestaurant(404L);
        });
    }

    @Test
    public void save(){
        Restaurant restaurant = Restaurant.builder().name("BeY").address("Busan").build();
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        restaurant = restaurantService.save(restaurant);
        assertThat(restaurant.getName(), is("BeY"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }

    @Test
    public void patchRestaurant(){
        Restaurant restaurant = Restaurant.builder().name("BeY").address("Busan").build();
        when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));
        restaurantService.patchRestaurant(1004L, "SOOL HOUSE", "Busan");
        assertThat(restaurant.getName(), is("SOOL HOUSE"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }
}