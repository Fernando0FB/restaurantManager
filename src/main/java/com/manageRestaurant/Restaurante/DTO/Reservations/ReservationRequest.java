package com.manageRestaurant.Restaurante.DTO.Reservations;

import com.manageRestaurant.Restaurante.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private LocalDate date;
    private LocalTime initialTime;
    private LocalTime finalTime;
    private ReservationStatus status;
    private Long customerId;
    private Long tableId;
}
