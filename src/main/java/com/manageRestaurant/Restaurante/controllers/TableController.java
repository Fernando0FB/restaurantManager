package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.models.UsersModel;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import com.manageRestaurant.Restaurante.services.TableService;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TableController {
    @Autowired
    private TablesRepository tablesRepository;

    @Autowired
    private TableService tableService;

    @GetMapping("/api/all-tables")
    public ResponseEntity<List<TablesModel>> allTables() {
        return ResponseEntity.ok(tablesRepository.findAll());
    }

    @PostMapping("/api/register-table")
    public ResponseEntity<String> createTable(@RequestBody TablesModel tablesModel) {
        Map<Boolean, String> created = tableService.createTable(tablesModel);
        Boolean isValid = created.keySet().iterator().next();
        String message = created.get(isValid);
        HttpStatus statusReq = isValid ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        String responseMessage = isValid ? "Mesa criada com sucesso!" : message;
        return new ResponseEntity<>(responseMessage, statusReq);
    }

}
