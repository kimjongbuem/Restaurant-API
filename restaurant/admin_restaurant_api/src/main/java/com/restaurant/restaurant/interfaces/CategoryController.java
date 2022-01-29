package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.CategoryService;
import com.restaurant.restaurant.application.RegionService;
import com.restaurant.restaurant.domain.Category;
import com.restaurant.restaurant.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorys")
    public List<Category> getCategorys(){
        return categoryService.getCategories();
    }

    @PostMapping("/categorys")
    public ResponseEntity<?> createCategory(@Validated @RequestBody Category resource) throws URISyntaxException {
        Category category = categoryService.createCategory(resource.getName());
        URI uri = new URI("/categorys/" + category.getId());
        return ResponseEntity.created(uri).body("{}");
    }
}
