package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {
    PENDING("Pendente"),
    CONFIRMED("Confirmada"),
    CANCELLED("Cancelada"),
    COMPLETED("Concluída");

    private final String message;
}
