package com.restaurant.restaurant.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();
    List<Restaurant> findAllByAddressContaining(String region);
    List<Restaurant> findAllByCategoryId(long categoryId);
    List<Restaurant> findAllByAddressContainingAndCategoryId(String region, long categoryId);
    Optional<Restaurant> findById(long id);
}
