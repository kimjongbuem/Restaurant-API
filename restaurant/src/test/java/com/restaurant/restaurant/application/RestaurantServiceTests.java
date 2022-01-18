package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.MenuItemRepository;
import com.restaurant.restaurant.domain.Restaurant;
import com.restaurant.restaurant.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        List<Restaurant> restaurantList = new ArrayList<>();
        Restaurant restaurant = new Restaurant("JOKER HOUSE", "Seoul");
        restaurantList.add(restaurant);
        restaurantList.add(new Restaurant("NUKER HOUSE", "Seoul"));
        when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.findAll()).thenReturn(restaurantList);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        assert restaurants != null;
        assertThat(restaurants.size(), is(2));
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantRepository.findById(1004L).orElse(null);
        assert restaurant != null;
        assertThat(restaurant.getName(), is("JOKER HOUSE"));
    }

    @Test
    public void save(){
        Restaurant restaurant = new Restaurant("BeY", "busan");
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        restaurant = restaurantRepository.save(restaurant);
        assertThat(restaurant.getName(), is("BeY"));
        assertThat(restaurant.getAddress(), is("busan"));
    }
}