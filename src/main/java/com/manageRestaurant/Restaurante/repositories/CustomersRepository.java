package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.CustomersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomersRepository extends JpaRepository<CustomersModel, Long>, JpaSpecificationExecutor<CustomersModel> {
    Optional<CustomersModel> findByDeletedIsFalseAndId(Long id);

    Optional<CustomersModel> findByCpf(String cpf);
}
