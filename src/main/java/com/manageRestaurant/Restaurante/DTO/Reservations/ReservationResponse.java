package com.manageRestaurant.Restaurante.DTO.Reservations;

import com.manageRestaurant.Restaurante.DTO.Customers.CustomersResponse;
import com.manageRestaurant.Restaurante.DTO.Tables.TablesResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private Long id;
    private LocalDate date;
    private LocalTime initialTime;
    private LocalTime finalTime;
    private String status;
    private CustomersResponse customer;
    private TablesResponse table;
}
