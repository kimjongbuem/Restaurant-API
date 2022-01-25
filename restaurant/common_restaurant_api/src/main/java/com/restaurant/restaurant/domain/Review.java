package com.restaurant.restaurant.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private long id;

    private long restaurantId;

    @NotEmpty
    private String name;

    @NotNull
    private Integer score;

    @NotEmpty
    private String description;
}
