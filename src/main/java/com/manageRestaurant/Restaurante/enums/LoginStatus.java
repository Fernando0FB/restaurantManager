package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginStatus {
    IS_VALID("Login efetuado com sucesso!"),
    INVALID_PASSWORD("Senha incorreta!"),
    USER_NOT_FOUND("Usuário não encontrado!");

    private final String message;
}
