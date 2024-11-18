package com.manageRestaurant.Restaurante.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity(name = "Customers")
@AllArgsConstructor
@NoArgsConstructor
public class CustomersModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String cpf;
    @NotNull
    private String phone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityModel entity;
}
