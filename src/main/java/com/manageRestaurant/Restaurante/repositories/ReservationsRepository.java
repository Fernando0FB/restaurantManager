package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsRepository extends JpaRepository<ReservationModel, Long> {
}
