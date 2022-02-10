package com.restaurant.restaurant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseSessionDto {
    private String accessToken;
}
