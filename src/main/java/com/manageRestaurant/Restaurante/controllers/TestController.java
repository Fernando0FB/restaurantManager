package com.manageRestaurant.Restaurante.controllers;

import com.manageRestaurant.Restaurante.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

@RestController
public class TestController {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationService authenticationService;

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

    @GetMapping("/all-tables")
    //public List<Tables> allTables (@RequestHeader (value = "Authorization") String codedAuthorization){
    public String allTables (@RequestHeader(value = "Authorization") String codedAuthorization){
        if(codedAuthorization.startsWith("Basic ")) {
            codedAuthorization = codedAuthorization.substring(6);
        }
        return new String(Base64.getDecoder().decode(codedAuthorization.trim()), StandardCharsets.UTF_8);
    }

    @GetMapping("/autenticate")
    public String autenticate (@RequestHeader(value = "Authorization") String codedAuthorization) {
        return authenticationService.authenticate(codedAuthorization) ? "Autenticado!" : "Não autenticado!";
    }
}