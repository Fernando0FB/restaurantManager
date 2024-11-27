package com.manageRestaurant.Restaurante.repositories.mappers;

import com.manageRestaurant.Restaurante.DTO.Customers.CustomersRequest;
import com.manageRestaurant.Restaurante.DTO.Customers.CustomersResponse;
import com.manageRestaurant.Restaurante.models.CustomersModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomersMapper {
    public static CustomersResponse toResponse(CustomersModel customer) {
        return new CustomersResponse(
                customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getPhone(),
                customer.getEmail()
        );
    }

    public List<CustomersResponse> toResponseList(List<CustomersModel> customersModels) {
        return customersModels.stream().map(CustomersMapper::toResponse).collect(Collectors.toList());
    }

    public CustomersModel toModel(CustomersRequest customersRequest) {
        CustomersModel customersModel = new CustomersModel();
        customersModel.setPhone(customersRequest.getPhone());
        customersModel.setName(customersRequest.getName());
        customersModel.setCpf(customersRequest.getCpf());
        customersModel.setEmail(customersRequest.getEmail());
        return customersModel;
    }
}
