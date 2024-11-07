package com.manageRestaurant.Restaurante.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private LocalDate date;
    private LocalTime initialTime;
    private LocalTime finalTime;
    private Long tableId;
}
