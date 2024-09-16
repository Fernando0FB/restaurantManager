package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    Optional<UsersModel> findByUsername(String username);

    Optional<UsersModel> findByCpf(String cpf);

    Optional<UsersModel> findByUsernameOrCpf(String cpf, String username);
}