package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.Category;
import com.restaurant.restaurant.domain.CategoryRepository;
import com.restaurant.restaurant.domain.Region;
import com.restaurant.restaurant.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryServiceTests {
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder()
                .name("dish")
                .build());

        when(categoryRepository.findAll()).thenReturn(categories);
        categories = categoryService.getCategories();
        assertThat(categories.get(0).getName(), is("dish"));
    }

    @Test
    public void createCategory(){
        Category category = Category.builder().name("dish").build();
        when(categoryRepository.save(category)).thenReturn(category);

        category = categoryService.createCategory("dish");
        assertThat(category.getName(), is("dish"));
        verify(categoryRepository).save(any());
    }
}