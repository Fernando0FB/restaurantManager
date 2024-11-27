package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.DTO.ApiResponse;
import com.manageRestaurant.Restaurante.DTO.Customers.CustomersRequest;
import com.manageRestaurant.Restaurante.DTO.Customers.CustomersResponse;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.models.CustomersModel;
import com.manageRestaurant.Restaurante.repositories.mappers.CustomersMapper;
import com.manageRestaurant.Restaurante.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    @Autowired
    private CustomersMapper customersMapper;
    @Autowired
    private CustomersService customersService;

    @GetMapping
    public ResponseEntity<List<CustomersResponse>> findAll(@RequestParam(required = false) Map<String, String> filters) {
        List<CustomersModel> reservationList = customersService.getAll(filters);
        return ResponseEntity.ok(customersMapper.toResponseList(reservationList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomersResponse> findById(@PathVariable Long id) {
        CustomersModel customer = customersService.getById(id);
        return customer != null ? ResponseEntity.ok(customersMapper.toResponse(customer)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CustomersRequest customersRequest) {
        CustomersModel customer = customersMapper.toModel(customersRequest);
        ValidationDTO validationResponse = customersService.create(customer);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody CustomersRequest customersRequest) {
        CustomersModel customer = customersMapper.toModel(customersRequest);
        customer.setId(id);
        ValidationDTO validationResponse = customersService.update(id, customer);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ValidationDTO validationResponse = customersService.delete(id);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }
}
