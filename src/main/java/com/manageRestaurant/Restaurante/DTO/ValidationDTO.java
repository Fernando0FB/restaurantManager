package com.manageRestaurant.Restaurante.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationDTO {
    private boolean success;
    private String message;
}
