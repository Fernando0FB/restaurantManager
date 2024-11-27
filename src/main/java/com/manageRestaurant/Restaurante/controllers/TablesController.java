package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.DTO.ApiResponse;
import com.manageRestaurant.Restaurante.DTO.Tables.TablesRequest;
import com.manageRestaurant.Restaurante.DTO.Tables.TablesResponse;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.repositories.mappers.TablesMapper;
import com.manageRestaurant.Restaurante.services.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tables")
public class TablesController {
    @Autowired
    private TablesService tablesService;

    @GetMapping
    public ResponseEntity<List<TablesResponse>> findAll(@RequestParam(required = false) Map<String, String> filters) {
        List<TablesModel> tablesList = tablesService.getAll(filters);
        return ResponseEntity.ok(TablesMapper.toResponseList(tablesList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TablesResponse> findById(@PathVariable Long id) {
        TablesModel table = tablesService.getById(id);
        return table != null ? ResponseEntity.ok(TablesMapper.toResponse(table)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody TablesRequest tablesRequest) {
        TablesModel table = TablesMapper.toModel(tablesRequest);
        ValidationDTO validationResponse = tablesService.create(table);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody TablesRequest tablesRequest) {
        TablesModel table = TablesMapper.toModel(tablesRequest);
        table.setId(id);
        ValidationDTO validationResponse = tablesService.update(id, table);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ValidationDTO validationResponse = tablesService.delete(id);
        HttpStatus statusReq = validationResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiResponse(validationResponse.getMessage()), statusReq);
    }

}
