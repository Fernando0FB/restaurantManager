package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.enums.TablesStatus;
import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    public ValidationDTO createTable(TablesModel table) {
        ValidationDTO validationResponse = checkIfTableAlreadyExist(table);
        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }
        tablesRepository.save(table);
        return new ValidationDTO(true, TablesStatus.SUCCESS.getMessage());
    }

    public ValidationDTO checkIfTableAlreadyExist (TablesModel table) {
        if(tablesRepository.findByNumber(table.getNumber()).isPresent()) {
            return new ValidationDTO(false, TablesStatus.TABLE_ALREADY_EXISTS.getMessage());
        }
        return new ValidationDTO(true, "");
    }
}
