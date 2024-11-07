package com.manageRestaurant.Restaurante.repositories;

import com.manageRestaurant.Restaurante.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationsRepository extends JpaRepository<ReservationModel, Long> {
    public List<ReservationModel> findByDate(LocalDate date);


}
