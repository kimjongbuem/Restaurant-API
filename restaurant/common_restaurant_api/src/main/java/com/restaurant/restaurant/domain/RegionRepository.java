package com.restaurant.restaurant.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Long> {
    List<Region> findAll();
    @SuppressWarnings("unchecked")
    Region save(Region region);

    List<Region> findAllByAddressContaining(String region);
}
