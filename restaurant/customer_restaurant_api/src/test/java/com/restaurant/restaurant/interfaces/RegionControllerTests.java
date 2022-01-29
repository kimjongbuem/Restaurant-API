package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.RegionService;
import com.restaurant.restaurant.domain.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegionController.class)
class RegionControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegionService regionService;

    @Test
    public void getRegions() throws Exception {
        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder()
                        .address("seoul")
                        .build());

        when(regionService.getRegions()).thenReturn(regions);

        mvc.perform(MockMvcRequestBuilders.get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("seoul")));
    }
}