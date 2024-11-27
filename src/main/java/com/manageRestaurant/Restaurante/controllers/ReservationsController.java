package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.DTO.ApiResponse;
import com.manageRestaurant.Restaurante.DTO.Reservations.ReservationRequest;
import com.manageRestaurant.Restaurante.DTO.Reservations.ReservationResponse;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.models.ReservationModel;
import com.manageRestaurant.Restaurante.repositories.mappers.ReservationMapper;
import com.manageRestaurant.Restaurante.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private ReservationsService reservationsService;

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> findAll(@RequestParam(required = false) Map<String, String> filters) {
        List<ReservationModel> reservationList = reservationsService.getAll(filters);
        return ResponseEntity.ok(reservationMapper.toResponseList(reservationList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> findById(@PathVariable Long id) {
        ReservationModel reservation = reservationsService.getById(id);
        return reservation != null ? ResponseEntity.ok(reservationMapper.toResponse(reservation)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody ReservationRequest reservationRequest) {
        ValidationDTO validationResponse = reservationsService.create(reservationRequest);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody ReservationRequest reservationRequest) {
        ValidationDTO validationResponse = reservationsService.update(id, reservationRequest);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ValidationDTO validationResponse = reservationsService.delete(id);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }
}
