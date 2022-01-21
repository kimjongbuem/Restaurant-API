package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.MenuItem;
import com.restaurant.restaurant.domain.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MenuItemServiceTests {

    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItem.builder().name("Kimchi").build());
        menuItems.add(MenuItem.builder().id(1L).name("quackdugi").build());
        menuItems.add(MenuItem.builder().id(1L).deleted(true).build());

        menuItemService.bulkUpdate(1L, menuItems);
        verify(menuItemRepository, times(2)).save(any());
        verify(menuItemRepository, times(1)).deleteById(1L);

    }
}