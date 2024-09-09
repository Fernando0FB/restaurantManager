package com.manageRestaurant.Restaurante.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
        @Id
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDate date;
        private LocalTime time;
        private String status;
        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customers customer;

        @OneToOne
        @JoinColumn(name = "tables_id")
        private Tables table;

}