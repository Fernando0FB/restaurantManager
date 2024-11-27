package com.manageRestaurant.Restaurante.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Data
@Getter
@Setter
@Entity(name = "tables")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tables SET deleted = true WHERE id=?")
public class TablesModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Integer capacity;
    private String observation;
    private Boolean deleted = Boolean.FALSE;
}
