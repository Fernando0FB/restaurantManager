package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
