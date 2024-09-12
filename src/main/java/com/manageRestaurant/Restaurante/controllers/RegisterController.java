package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.models.Users;
import com.manageRestaurant.Restaurante.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/registerUser")
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        Map<Boolean, String> created = usersService.createUser(user);
        Boolean isValid = created.keySet().iterator().next();
        String message = created.get(isValid);
        HttpStatus statusReq = isValid ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        String responseMessage = isValid ? "Usuário criado com sucesso!" : message;
        return new ResponseEntity<>(responseMessage, statusReq);
    }
}
