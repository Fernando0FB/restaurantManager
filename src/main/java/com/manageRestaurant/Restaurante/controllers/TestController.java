package com.manageRestaurant.Restaurante.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class TestController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-connection")
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
}