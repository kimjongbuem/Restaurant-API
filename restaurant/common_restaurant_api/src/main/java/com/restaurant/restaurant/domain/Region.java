package com.restaurant.restaurant.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String address;
}
