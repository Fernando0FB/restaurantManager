package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.DTO.ApiResponse;
import com.manageRestaurant.Restaurante.DTO.Users.UsersPutRequest;
import com.manageRestaurant.Restaurante.DTO.Users.UsersRequest;
import com.manageRestaurant.Restaurante.DTO.Users.UsersResponse;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.models.UsersModel;
import com.manageRestaurant.Restaurante.repositories.mappers.UsersMapper;
import com.manageRestaurant.Restaurante.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<UsersResponse>> findAll(@RequestParam(required = false) Map<String, String> filters) {
        List<UsersModel> usersModelList = usersService.getAll(filters);
        return ResponseEntity.ok(usersMapper.toResponseList(usersModelList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> findById(@PathVariable Long id) {
        UsersModel user = usersService.getById(id);
        return user != null ? ResponseEntity.ok(usersMapper.toResponse(user)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody UsersRequest user) {
        UsersModel newUser = usersMapper.toModel(user);
        ValidationDTO validationResponse = usersService.create(newUser);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UsersPutRequest user) {
        UsersModel userModel = usersMapper.toModel(user);
        ValidationDTO validationResponse = usersService.update(id, userModel);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        ValidationDTO validationResponse = usersService.delete(id);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }
}
