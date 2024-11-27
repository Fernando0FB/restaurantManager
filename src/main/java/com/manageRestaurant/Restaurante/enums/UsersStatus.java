package com.manageRestaurant.Restaurante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsersStatus {
    USER_NOT_FOUND("Usuário não encontrado"),
    USERNAME_ALREADY_EXISTS("Já existe um usuário cadastrado com esse username!"),
    CPF_ALREADY_EXISTS("Já existe um usuário cadastrado com esse CPF!"),
    SUCCESS("Usuário criado com sucesso!"),
    SUCCESS_DELETED("Usuário deletado com sucesso!");

    private final String message;
}
