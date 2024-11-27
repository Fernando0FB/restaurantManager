package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ReservationsRepository extends JpaRepository<ReservationModel, Long>, JpaSpecificationExecutor<ReservationModel> {
    Optional<ReservationModel> findByDeletedIsFalseAndId(Long id);
}
