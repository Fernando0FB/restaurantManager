package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
}
