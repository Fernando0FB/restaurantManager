package com.manageRestaurant.Restaurante.repositories.mappers;

import com.manageRestaurant.Restaurante.DTO.Tables.TablesRequest;
import com.manageRestaurant.Restaurante.DTO.Tables.TablesResponse;
import com.manageRestaurant.Restaurante.models.TablesModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TablesMapper {
    public static TablesModel toModel(TablesRequest tablesRequest) {
        TablesModel tablesModel = new TablesModel();
        tablesModel.setObservation(tablesRequest.getObservation());
        tablesModel.setCapacity(tablesRequest.getCapacity());
        tablesModel.setNumber(tablesRequest.getNumber());
        return tablesModel;
    }

    public TablesRequest toRequest(TablesModel tablesModel) {
        TablesRequest tablesRequest = new TablesRequest();
        tablesRequest.setObservation(tablesModel.getObservation());
        tablesRequest.setCapacity(tablesModel.getCapacity());
        tablesRequest.setNumber(tablesModel.getNumber());
        return tablesRequest;
    }

    public static TablesResponse toResponse(TablesModel tablesModel) {
        TablesResponse tablesResponse = new TablesResponse();
        tablesResponse.setId(tablesModel.getId());
        tablesResponse.setObservation(tablesModel.getObservation());
        tablesResponse.setCapacity(tablesModel.getCapacity());
        tablesResponse.setNumber(tablesModel.getNumber());
        return tablesResponse;
    }

    public static List<TablesResponse> toResponseList(List<TablesModel> list) {
        return list.stream().map(TablesMapper::toResponse).collect(Collectors.toList());
    }
}
