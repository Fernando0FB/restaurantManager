package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.enums.TablesRequestStatus;
import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import com.manageRestaurant.Restaurante.repositories.specifications.TablesSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TablesService {
    @Autowired
    private TablesRepository tablesRepository;

    public TablesModel getById(Long id) {
        return tablesRepository.findByDeletedIsFalseAndId(id).orElse(null);
    }

    public List<TablesModel> getAll(Map<String, String> filters) {
        return tablesRepository.findAll(TablesSpecification.columnEqual(filters));
    }

    public ValidationDTO create(TablesModel table) {
        ValidationDTO validationResponse = checkIfTableAlreadyExist(table);
        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }
        tablesRepository.save(table);
        return new ValidationDTO(true, TablesRequestStatus.SUCCESS.getMessage());
    }

    public ValidationDTO update(Long id, TablesModel table) {
        Optional<TablesModel> optTable = tablesRepository.findByDeletedIsFalseAndId(id);
        if (optTable.isEmpty()) {
            return new ValidationDTO(false, TablesRequestStatus.TABLE_NOT_FOUND.getMessage());
        }

        if (!checkIfTableAlreadyExist(table).isSuccess()) {
            return new ValidationDTO(false, TablesRequestStatus.TABLE_ALREADY_EXISTS.getMessage());
        }

        TablesModel currentTable = optTable.get();

        currentTable.setNumber(table.getNumber());
        currentTable.setCapacity(table.getCapacity());
        currentTable.setObservation(table.getObservation());
        tablesRepository.save(currentTable);

        return new ValidationDTO(true, TablesRequestStatus.TABLE_UPDATED.getMessage());
    }

    public ValidationDTO delete(Long tableId) {
        Optional<TablesModel> optTable = tablesRepository.findById(tableId);
        if (optTable.isEmpty()) {
            return new ValidationDTO(false, TablesRequestStatus.TABLE_NOT_FOUND.getMessage());
        }
        tablesRepository.delete(optTable.get());
        return new ValidationDTO(true, TablesRequestStatus.DELETED.getMessage());
    }

    public ValidationDTO checkIfTableAlreadyExist(TablesModel table) {
        Optional<TablesModel> optTables = tablesRepository.findByNumber(table.getNumber());
        if (optTables.isPresent() && !optTables.get().getId().equals(table.getId())) {
            return new ValidationDTO(false, TablesRequestStatus.TABLE_ALREADY_EXISTS.getMessage());
        }
        return new ValidationDTO(true, "");
    }
}
