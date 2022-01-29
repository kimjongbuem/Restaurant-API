package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.RegionService;
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
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> getRegions(){
        return regionService.getRegions();
    }

    @PostMapping("/regions")
    public ResponseEntity<?> createRegion(@Validated @RequestBody Region resource) throws URISyntaxException {
        Region region = regionService.createRegion(resource.getAddress());
        URI uri = new URI("/regions/" + region.getId());
        return ResponseEntity.created(uri).body("{}");
    }

}
