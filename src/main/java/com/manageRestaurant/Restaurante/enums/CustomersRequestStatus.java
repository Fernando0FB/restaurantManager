package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomersRequestStatus {
    SUCCESS_CREATED("Cliente criado com sucesso!"),
    CUSTOMER_NOT_FOUND("Cliente não encontrado!"),
    CUSTOMER_UPDATED("Cliente atualizado com sucesso!"),
    DELETED("Cliente excluido com sucesso!"),
    CUSTOMER_ALREADY_EXIST("Já existe um cliente com esse CPF");

    private String message;
}
