package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
}
