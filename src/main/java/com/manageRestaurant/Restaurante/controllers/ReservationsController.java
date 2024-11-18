package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.DTO.ReservationDTO;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.models.ReservationModel;
import com.manageRestaurant.Restaurante.repositories.ReservationsRepository;
import com.manageRestaurant.Restaurante.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationsController {
    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private ReservationsService reservationsService;

    @GetMapping("/api/all-reservations")
    public ResponseEntity<List<ReservationModel>> allReservations() {
        return ResponseEntity.ok(reservationsRepository.findAll());
    }

    @PostMapping("/api/register-reservation")
    public ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ValidationDTO validationResponse = reservationsService.createReservation(reservationDTO);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(validationResponse.getMessage(), statusReq);
    }
}
