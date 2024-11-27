package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Long>, JpaSpecificationExecutor<UsersModel> {
    Optional<UsersModel> findByCpf(String cpf);

    Optional<UsersModel> findByUsername(String username);

    Optional<UsersModel> findByDeletedIsFalseAndId(Long id);
}