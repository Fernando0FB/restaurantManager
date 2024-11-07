package com.manageRestaurant.Restaurante.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity(name = "tables")
@NoArgsConstructor
@AllArgsConstructor
public class TablesModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Integer capacity;
    private String observation;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityModel entity;
}
