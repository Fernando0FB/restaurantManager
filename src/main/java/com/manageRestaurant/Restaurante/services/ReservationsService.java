package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.DTO.Reservations.ReservationRequest;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.enums.ReservationRequestStatus;
import com.manageRestaurant.Restaurante.models.CustomersModel;
import com.manageRestaurant.Restaurante.models.ReservationModel;
import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.repositories.CustomersRepository;
import com.manageRestaurant.Restaurante.repositories.ReservationsRepository;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import com.manageRestaurant.Restaurante.repositories.specifications.ReservationSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReservationsService {
    @Autowired
    private ReservationsRepository reservationsRepository;
    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private CustomersRepository customersRepository;

    public ReservationModel getById(Long id) {
        return reservationsRepository.findByDeletedIsFalseAndId(id).orElse(null);
    }

    public List<ReservationModel> getAll(Map<String, String> filters) {
        return reservationsRepository.findAll(ReservationSpecification.columnEqual(filters));
    }

    public ValidationDTO create(ReservationRequest reservationDTO) {
        TablesModel table = tablesRepository.findById(reservationDTO.getTableId())
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        CustomersModel customer = customersRepository.findById(reservationDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        ReservationModel reservation = new ReservationModel();
        reservation.setDate(reservationDTO.getDate());
        reservation.setInitialTime(reservationDTO.getInitialTime());
        reservation.setFinalTime(reservationDTO.getFinalTime());
        reservation.setTable(table);
        reservation.setCustomer(customer);

        reservationsRepository.save(reservation);
        return new ValidationDTO(true, ReservationRequestStatus.SUCCESS.getMessage());
    }

    public ValidationDTO update(Long id, ReservationRequest reservationRequest) {
        Optional<ReservationModel> optReservation = reservationsRepository.findById(id);
        if (optReservation.isEmpty()) {
            return new ValidationDTO(false, ReservationRequestStatus.RESERVATION_NOT_FOUND.getMessage());
        }

        ReservationModel currentReservation = optReservation.get();

        currentReservation.setDate(reservationRequest.getDate());
        currentReservation.setInitialTime(reservationRequest.getInitialTime());
        currentReservation.setFinalTime(reservationRequest.getFinalTime());
        currentReservation.setTable(tablesRepository.findById(reservationRequest.getTableId()).get());
        currentReservation.setCustomer(customersRepository.findById(reservationRequest.getCustomerId()).get());
        currentReservation.setStatus(reservationRequest.getStatus().toString());
        reservationsRepository.save(currentReservation);

        return new ValidationDTO(true, ReservationRequestStatus.SUCCESS.getMessage());
    }

    public ValidationDTO delete(Long id) {
        Optional<ReservationModel> reservation = reservationsRepository.findByDeletedIsFalseAndId(id);
        if (reservation.isEmpty()) {
            return new ValidationDTO(false, ReservationRequestStatus.RESERVATION_NOT_FOUND.getMessage());
        }
        reservationsRepository.delete(reservation.get());
        return new ValidationDTO(true, ReservationRequestStatus.SUCCESS_DELETED.getMessage());
    }

}
