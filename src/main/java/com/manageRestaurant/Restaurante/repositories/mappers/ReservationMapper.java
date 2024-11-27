package com.manageRestaurant.Restaurante.repositories.mappers;

import com.manageRestaurant.Restaurante.DTO.Reservations.ReservationResponse;
import com.manageRestaurant.Restaurante.models.ReservationModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;



@Component
public class ReservationMapper {
    public ReservationResponse toResponse(ReservationModel reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getDate(),
                reservation.getInitialTime(),
                reservation.getFinalTime(),
                reservation.getStatus(),
                reservation.getCustomer() != null ? CustomersMapper.toResponse(reservation.getCustomer()) : null,
                reservation.getTable() != null ? TablesMapper.toResponse(reservation.getTable()) : null
        );
    }

    public List<ReservationResponse> toResponseList(List<ReservationModel> reservations) {
        return reservations.stream().map(this::toResponse).collect(Collectors.toList());
    }
}