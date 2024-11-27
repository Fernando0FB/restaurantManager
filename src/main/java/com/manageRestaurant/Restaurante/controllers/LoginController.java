package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.DTO.ApiResponse;
import com.manageRestaurant.Restaurante.DTO.Users.LoginRequest;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private UsersService usersService;

    @PostMapping()
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        ValidationDTO validationResponse = usersService.authenticateUserCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        HttpStatus status = validationResponse.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        ApiResponse response = new ApiResponse(validationResponse.getMessage());
        return new ResponseEntity<>(response, status);
    }

}