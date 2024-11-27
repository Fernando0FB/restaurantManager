package com.manageRestaurant.Restaurante.models;

import com.manageRestaurant.Restaurante.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Data
@Entity(name = "Users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
public class UsersModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String username;
    private String password;
    private Roles role;
    private Boolean deleted;

}