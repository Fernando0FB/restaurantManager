package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.models.TablesModel;
import com.manageRestaurant.Restaurante.repositories.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TablesRepository tablesRepository;

    public Map<Boolean, String> createTable(TablesModel table) {
        Map<Boolean, String> response = new HashMap();

        Map<Boolean, String> alreadyExist = checkIfTableAlreadyExist(table);
        Boolean exist = alreadyExist.keySet().iterator().next();
        if(exist) {
            String message = alreadyExist.get(exist);
            response.put(false, message);
            return response;
        }
        tablesRepository.save(table);
        response.put(true, "");
        return response;
    }

    public Map<Boolean, String> checkIfTableAlreadyExist (TablesModel table) {
        Map response = new HashMap();
        Optional<TablesModel> tableInstance = tablesRepository.findByNumber(table.getNumber());
        if (tableInstance.isPresent()) {
            response.put(true, "Já existe uma mesa cadastrada com esse número!");
            return response;
        }
        response.put(false, "");
        return response;
    }
}
