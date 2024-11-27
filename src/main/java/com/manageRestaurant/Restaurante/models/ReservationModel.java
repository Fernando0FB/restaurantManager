package com.manageRestaurant.Restaurante.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@Entity(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
@SQLDelete(sql = "UPDATE reservation SET deleted = true WHERE id=?")
public class ReservationModel {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime initialTime;
    private LocalTime finalTime;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private CustomersModel customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "tables_id")
    private TablesModel table;

    private Boolean deleted = Boolean.FALSE;

}