package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.DTO.validationDTO;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import com.manageRestaurant.Restaurante.services.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableController {
    @Autowired
    private TablesRepository tablesRepository;

    @Autowired
    private TablesService tableService;

    @GetMapping("/api/all-tables")
    public ResponseEntity<List<TablesModel>> allTables() {
        return ResponseEntity.ok(tablesRepository.findAll());
    }

    @PostMapping("/api/register-table")
    public ResponseEntity<String> createTable(@RequestBody TablesModel tablesModel) {
        validationDTO validationResponse = tableService.createTable(tablesModel);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(validationResponse.getMessage(), statusReq);
    }

}
