package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TablesStatus {
    TABLE_ALREADY_EXISTS("Já existe uma mesa cadastrada com esse nome!"),
    SUCCESS("Mesa criada com sucesso!");

    private final String message;
}
