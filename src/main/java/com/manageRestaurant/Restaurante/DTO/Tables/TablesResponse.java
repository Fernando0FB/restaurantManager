package com.manageRestaurant.Restaurante.DTO.Tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablesResponse {
    private Long id;
    private Integer number;
    private Integer capacity;
    private String observation;
}
