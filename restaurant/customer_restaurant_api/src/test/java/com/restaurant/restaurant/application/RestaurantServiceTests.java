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

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantService restaurantService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
        testInitGetRestaurants();
        testInitGetRestaurant();
    }

    public void testInitGetRestaurants(){
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(Restaurant.builder().name("JOKER HOUSE").categoryId(1L).address("Seoul").build());
        restaurantList.add(Restaurant.builder().name("NUKER HOUSE").categoryId(1L).address("Seoul").build());
        when(restaurantRepository.findAll()).thenReturn(restaurantList);
        when(restaurantRepository.findAllByAddressContaining("Seoul")).thenReturn(restaurantList);
        when(restaurantRepository.findAllByCategoryId(1L)).thenReturn(restaurantList);
        when(restaurantRepository.findAllByAddressContainingAndCategoryId("Seoul", 1L)).thenReturn(restaurantList);
    }

    public void testInitGetRestaurant(){
        Restaurant restaurant = Restaurant.builder().name("JOKER HOUSE").address("Seoul").build();

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("kimchi").build());

        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder().restaurantId(1004L).name("JOKER").score(3).description("soso").build());

        when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.findByRestaurantId(1004L)).thenReturn(menuItems);
        when(reviewRepository.findByRestaurantId(1004L)).thenReturn(reviews);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        assert restaurants != null;
        assertThat(restaurants.size(), is(2));
    }

    @Test
    public void getRestaurantsByAddress(){
        List<Restaurant> restaurants = restaurantService.getRestaurants("Seoul");
        assert restaurants != null;
        assertThat(restaurants.size(), is(2));
    }

    @Test
    public void getRestaurantsByCategoryId(){
        List<Restaurant> restaurants = restaurantService.getRestaurants(1L);
        assert restaurants != null;
        assertThat(restaurants.size(), is(2));
    }

    @Test
    public void getRestaurantsByAddressAndCategoryId(){
        List<Restaurant> restaurants = restaurantService.getRestaurants("Seoul", 1L);
        assert restaurants != null;
        assertThat(restaurants.size(), is(2));
    }


    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assert restaurant != null;
        assertThat(restaurant.getName(), is("JOKER HOUSE"));
        assertThat(restaurant.getMenuItems().get(0).getName(), is("kimchi"));
        assertThat(restaurant.getReviews().get(0).getDescription(), is("soso"));
    }

    @Test
    public void getInValidRestaurant(){
        assertThrows(RestaurantNotFoundException.class, () -> {
            Restaurant restaurant = restaurantService.getRestaurant(404L);
        });
    }
}