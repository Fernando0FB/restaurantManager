package com.manageRestaurant.Restaurante.DTO.Customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersResponse {
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private String email;
}
