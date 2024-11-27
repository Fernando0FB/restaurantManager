package com.manageRestaurant.Restaurante.DTO.Users;

import com.manageRestaurant.Restaurante.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    private Long id;
    private String name;
    private String cpf;
    private String username;
    private Roles role;
}
