package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.MenuItemService;
import com.restaurant.restaurant.domain.MenuItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MenuItemController.class)
class MenuItemControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;

    @Test
    public void getMenuItems() throws Exception {
        mvc.perform(get("/restaurants/1/menuitems")).andExpect(status().isOk());
        verify(menuItemService).getMenuItems(1L);
    }


    @Test
    public void bulkUpdate() throws Exception {

        List<MenuItem> menuItems = new ArrayList<>();

        mvc.perform(patch("/restaurants/1/menuitems").
                contentType(MediaType.APPLICATION_JSON).
                content("[]")).andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(1L, menuItems);

    }
}