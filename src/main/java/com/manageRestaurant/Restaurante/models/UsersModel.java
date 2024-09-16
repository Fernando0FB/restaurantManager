package com.manageRestaurant.Restaurante.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String username;
    private String password; // Armazene de forma segura (criptografada)
}