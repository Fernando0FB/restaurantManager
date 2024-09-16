package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.models.UsersModel;
import com.manageRestaurant.Restaurante.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsuarioController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/api/registerUser")
    public ResponseEntity<String> createUser(@RequestBody UsersModel user) {
        Map<Boolean, String> created = usersService.createUser(user);
        Boolean isValid = created.keySet().iterator().next();
        String message = created.get(isValid);
        HttpStatus statusReq = isValid ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        String responseMessage = isValid ? "Usuário criado com sucesso!" : message;
        return new ResponseEntity<>(responseMessage, statusReq);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody Map<String, String> loginRequest
    ) {
        Map<String, String> response = new HashMap<>();
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        Map<Boolean, String> authenticated = usersService.authenticateUserCredentials(username, password);

        Boolean isValid = authenticated.keySet().iterator().next();
        String message = authenticated.get(isValid);

        if (isValid) {
            response.put("message", "Login realizado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
