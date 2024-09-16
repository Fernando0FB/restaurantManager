package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.TablesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablesRepository extends JpaRepository<TablesModel, Long> {
}
