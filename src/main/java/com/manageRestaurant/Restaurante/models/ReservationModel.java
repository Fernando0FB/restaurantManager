package com.manageRestaurant.Restaurante.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@Entity(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
public class ReservationModel {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String teste;
    private LocalTime time;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersModel customer;

    @OneToOne
    @JoinColumn(name = "tables_id")
    private TablesModel table;


}