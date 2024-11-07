package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.models.UsersModel;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.repositories.UsersRepository;
import com.manageRestaurant.Restaurante.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @PostMapping("/registerUser")
    public ResponseEntity<String> createUser(@RequestBody UsersModel user) {
        ValidationDTO validationResponse = usersService.createUser(user);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(validationResponse.getMessage(), statusReq);
    }

    @GetMapping("/api/all-users")
    public ResponseEntity<List<UsersModel>> allTables() {
        return ResponseEntity.ok(usersRepository.findAll());
    }

}
