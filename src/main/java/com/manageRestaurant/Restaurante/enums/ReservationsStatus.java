package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationsStatus {
    SUCCESS("Reserva efetuada com sucesso!"),
    RESERVATION_ALREADY_EXISTS("Reserva já existente!");

    private final String message;
}
