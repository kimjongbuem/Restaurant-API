package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.MenuItem;
import com.restaurant.restaurant.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems(long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }
    public void bulkUpdate(long restaurantId, List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            update(restaurantId, menuItem);
        }
    }

    public void update(long restaurantId, MenuItem menuItem){
        if(menuItem.isDeleted()){
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }

}
