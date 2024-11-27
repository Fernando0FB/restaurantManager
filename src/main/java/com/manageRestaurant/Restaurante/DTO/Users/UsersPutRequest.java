package com.manageRestaurant.Restaurante.DTO.Users;

import com.manageRestaurant.Restaurante.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersPutRequest {
    private String name;
    private String cpf;
    private String username;
    private Roles role;
}