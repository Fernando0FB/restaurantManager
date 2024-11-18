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
    private LocalTime initialTime;
    private LocalTime finalTime;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersModel customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tables_id")
    private TablesModel table;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityModel entity;

}