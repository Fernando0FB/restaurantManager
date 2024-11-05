package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.enums.TablesStatus;
import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.DTO.validationDTO;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    public validationDTO createTable(TablesModel table) {
        validationDTO validationResponse = checkIfTableAlreadyExist(table);
        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }
        tablesRepository.save(table);
        return new validationDTO(true, TablesStatus.SUCCESS.getMessage());
    }

    public validationDTO checkIfTableAlreadyExist (TablesModel table) {
        if(tablesRepository.findByNumber(table.getNumber()).isPresent()) {
            return new validationDTO(false, TablesStatus.TABLE_ALREADY_EXISTS.getMessage());
        }
        return new validationDTO(true, "");
    }
}
