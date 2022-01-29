package com.restaurant.restaurant.application;

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

class RegionServiceTests {
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        regionService = new RegionService(regionRepository);
    }

    @Test
    public void getRegions(){
        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder()
                .address("seoul")
                .build());

        when(regionRepository.findAll()).thenReturn(regions);
        regions = regionService.getRegions();
        assertThat(regions.get(0).getAddress(), is("seoul"));
    }
}