package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.MenuItemService;
import com.restaurant.restaurant.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public void bulkUpdate(@PathVariable("restaurantId") long restaurantId, @RequestBody List<MenuItem> menuItems) {
        menuItemService.bulkUpdate(restaurantId, menuItems);
    }
}
