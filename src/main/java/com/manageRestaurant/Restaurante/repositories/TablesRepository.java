package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.TablesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TablesRepository extends JpaRepository<TablesModel, Long>, JpaSpecificationExecutor<TablesModel> {
    Optional<TablesModel> findByNumber(Integer number);

    Optional<TablesModel> findByDeletedIsFalseAndId(Long id);
}
