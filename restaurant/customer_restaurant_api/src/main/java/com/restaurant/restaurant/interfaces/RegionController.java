package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.RegionService;
import com.restaurant.restaurant.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> getRegions(){
        return regionService.getRegions();
    }

}
