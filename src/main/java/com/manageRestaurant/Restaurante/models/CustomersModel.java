package com.manageRestaurant.Restaurante.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.SQLDelete;

@Data
@Entity(name = "customers")
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE id=?")
public class CustomersModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String cpf;
    @NotNull
    private String phone;
    private String email;
    private Boolean deleted = Boolean.FALSE;
}
