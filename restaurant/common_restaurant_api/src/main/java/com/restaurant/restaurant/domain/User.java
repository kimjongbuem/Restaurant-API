package com.restaurant.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User {
    @GeneratedValue
    @Id
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    private String password;

    @NotNull
    private long level;

    public boolean isAdmin(){
        return level >= 3;
    }
    public boolean isActive(){
        return level != 0;
    }

    @JsonIgnore
    public String getAccessToken() {
        return password.substring(0,10);
    }
}
