package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.MenuItemRepository;
import com.restaurant.restaurant.domain.Restaurant;
import com.restaurant.restaurant.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
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

    @Mock
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
        List<Restaurant> restaurantList = new ArrayList<>();
        Restaurant restaurant = new Restaurant("JOKER HOUSE", "Seoul");
        restaurantList.add(restaurant);
        restaurantList.add(new Restaurant("NUKER HOUSE", "Seoul"));
        when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.findAll()).thenReturn(restaurantList);
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
    public void save(){
        Restaurant restaurant = new Restaurant("BeY", "busan");
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        restaurant = restaurantService.save(restaurant);
        assertThat(restaurant.getName(), is("BeY"));
        assertThat(restaurant.getAddress(), is("busan"));
    }

    @Test
    public void patchRestaurant(){
        Restaurant restaurant = new Restaurant("BeY", "busan");
        when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));
        restaurantService.patchRestaurant(1004L, "SOOL HOUSE", "Busan");
        assertThat(restaurant.getName(), is("SOOL HOUSE"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }
}