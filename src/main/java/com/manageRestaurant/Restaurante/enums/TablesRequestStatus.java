package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TablesRequestStatus {
    TABLE_ALREADY_EXISTS("Já existe uma mesa cadastrada com esse número!"),
    SUCCESS("Mesa criada com sucesso!"),
    DELETED("Mesa deletada com sucesso!"),
    TABLE_UPDATED("Mesa atualizada com sucesso"),
    TABLE_UPDATE_ALREADY_EXISTS("Já existe uma mesa cadastrada com esse número!"),
    TABLE_NOT_FOUND("Mesa não encontrada!");

    private final String message;
}
