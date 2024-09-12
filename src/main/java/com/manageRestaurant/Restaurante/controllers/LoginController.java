package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.services.AuthenticationService;
import com.manageRestaurant.Restaurante.services.UsersService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(
                                                        @RequestBody Map<String, String> loginRequest
                                                    ) {
        Map<String, String> response = new HashMap<>();

        if (!loginRequest.containsKey("username") || !loginRequest.containsKey("password") ||
                loginRequest.get("username").isEmpty() || loginRequest.get("password").isEmpty()) {

            response.put("message", "O corpo da requisição deve conter os campos 'username' e 'password', ambos preenchidos.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String username = loginRequest.get("user");
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
