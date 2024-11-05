package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.TablesModel;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TablesRepository extends JpaRepository<TablesModel, Long> {

    Optional<TablesModel> findByNumber(Integer number);
}
