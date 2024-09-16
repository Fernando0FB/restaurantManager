package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TablesRepository tablesRepository;

    @GetMapping("/api/test-connection")
    public String testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null) {
                return "Conexão com o banco de dados estabelecida com sucesso!";
            } else {
                return "Falha na conexão com o banco de dados.";
            }
        } catch (SQLException e) {
            return "Erro ao conectar ao banco de dados: " + e.getMessage();
        }
    }

    @GetMapping("/api/all-tables")
    //public List<Tables> allTables (@RequestHeader (value = "Authorization") String codedAuthorization){
    public ResponseEntity<List<TablesModel>> allTables() {
        return ResponseEntity.ok(tablesRepository.findAll());
    }
}