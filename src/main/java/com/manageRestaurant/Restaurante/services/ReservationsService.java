package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.DTO.ReservationDTO;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.enums.ReservationsStatus;
import com.manageRestaurant.Restaurante.models.ReservationModel;
import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.repositories.ReservationsRepository;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationsService {

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private TablesRepository tablesRepository;

    public ValidationDTO createReservation(ReservationDTO reservationDTO) {
        TablesModel table = tablesRepository.findById(reservationDTO.getTableId())
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        ReservationModel reservation = new ReservationModel();
        reservation.setDate(reservationDTO.getDate());
        reservation.setInitialTime(reservationDTO.getInitialTime());
        reservation.setFinalTime(reservationDTO.getFinalTime());
        reservation.setTable(table);

        reservationsRepository.save(reservation);
        return new ValidationDTO(true, ReservationsStatus.SUCCESS.getMessage());
    }
}
