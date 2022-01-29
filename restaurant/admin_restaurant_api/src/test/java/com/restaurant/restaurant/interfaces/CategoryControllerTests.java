package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.CategoryService;
import com.restaurant.restaurant.application.RegionService;
import com.restaurant.restaurant.domain.Category;
import com.restaurant.restaurant.domain.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void getCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder()
                        .name("dish")
                        .build());

        when(categoryService.getCategories()).thenReturn(categories);

        mvc.perform(MockMvcRequestBuilders.get("/categorys"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("dish")));
    }

    @Test
    public void createCategory() throws Exception {
        Category category = Category.builder().name("dish").build();

        when(categoryService.createCategory("dish")).thenReturn(category);

        mvc.perform(post("/categorys")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"dish\"}"))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) header().string("location", "/categorys/"+category.getId()))
                .andExpect(content().string(containsString("{}")));

        verify(categoryService).createCategory("dish");
    }

    @Test
    public void createInValidRegion() throws Exception {
        mvc.perform(post("/categorys")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

}