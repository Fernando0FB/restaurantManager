package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {
    ADMIN("admin"),
    USER("user");

    private String role;
}
