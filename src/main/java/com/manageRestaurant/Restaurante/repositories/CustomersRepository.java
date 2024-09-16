package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.CustomersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<CustomersModel, Long> {
}
