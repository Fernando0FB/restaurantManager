package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Tables, Long> {
}
