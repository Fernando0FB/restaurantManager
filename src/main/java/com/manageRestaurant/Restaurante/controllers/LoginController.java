package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.DTO.LoginRequestDTO;
import com.manageRestaurant.Restaurante.DTO.ResponseDTO;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/api/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        ValidationDTO validationResponse = usersService.authenticateUserCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        HttpStatus status = validationResponse.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        ResponseDTO response = new ResponseDTO(validationResponse.getMessage());
        return new ResponseEntity<>(response, status);
    }

}
