package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationRequestStatus {
    SUCCESS("Reserva efetuada com sucesso!"),
    RESERVATION_ALREADY_EXISTS("Reserva já existente!"),
    SUCCESS_DELETED("Reserva deletada com sucesso!"),
    RESERVATION_NOT_FOUND("Reserva não encontrada!");

    private final String message;
}
