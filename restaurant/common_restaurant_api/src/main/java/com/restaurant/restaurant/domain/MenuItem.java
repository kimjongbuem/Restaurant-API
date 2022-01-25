package com.restaurant.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue
    private long id;

    private long restaurantId;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean deleted;
}
