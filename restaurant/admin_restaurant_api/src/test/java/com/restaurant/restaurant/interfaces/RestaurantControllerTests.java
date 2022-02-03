package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.RestaurantService;
import com.restaurant.restaurant.domain.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    /*
    @SpyBean(구현체.class) controller에 의존성주입. ( 객체를 다양하게 변형 가능 )
    컨트롤러가 아니라면 즉 서비스는 @Before를 가진 메서드를 통해 의존성 주입을 직접해줘야한다.(Service)
    * */

    @BeforeEach
    public void setUp(){
        List<Restaurant> restaurantList = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder().name("JOKER HOUSE").address("Seoul").build();
        restaurantList.add(restaurant);
        restaurantList.add(Restaurant.builder().name("NUKER HOUSE").address("Seoul").build());
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        given(restaurantService.getRestaurants()).willReturn(restaurantList);
    }

    @Test
    public void getRestaurants() throws Exception {

         mvc.perform(get("/restaurants")).andExpect(status().isOk())
                 .andExpect(content().string(containsString("\"name\":\"JOKER HOUSE\"")))
                 .andExpect(content().string(containsString("\"name\":\"NUKER HOUSE\"")));

    }

    @Test
    public void getRestaurant() throws Exception {
        mvc.perform(get("/restaurants/1004")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"JOKER HOUSE\"")));
    }

    @Test
    public void getInValidRestaurant() throws Exception {
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404")).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("{}")));
    }

    @Test
    public void save() throws Exception {

        Restaurant restaurant = Restaurant.builder().categoryId(1L).name("JOKER HOUSE").address("Seoul").build();

        restaurantService.save(restaurant);

        mvc.perform(post("/restaurants").
                contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"JOKER HOUSE\", \"address\":\"seoul\", \"categoryId\":1}"))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) header().string("location", "/restaurant/"+restaurant.getId()))
                .andExpect(content().string(containsString("{}")));

        verify(restaurantService).save(restaurant);
    }

    @Test
    public void inValidSave() throws Exception {
        mvc.perform(post("/restaurants").
                contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"\", \"address\":\"\", \"categoryId\":1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void patchRestaurant() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"SOOL HOUSE\", \"address\":\"Busan\", \"categoryId\":1}"))
                .andExpect(status().isOk());

        verify(restaurantService).patchRestaurant(1004L, "SOOL HOUSE", "Busan");
    }

    @Test
    public void invalidPatchRestaurant() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"\", \"address\":\"\", \"categoryId\":1}"))
                .andExpect(status().isBadRequest());
    }
}