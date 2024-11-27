package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.enums.CustomersRequestStatus;
import com.manageRestaurant.Restaurante.models.CustomersModel;
import com.manageRestaurant.Restaurante.repositories.CustomersRepository;
import com.manageRestaurant.Restaurante.repositories.specifications.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    public CustomersModel getById(Long id) {
        return customersRepository.findByDeletedIsFalseAndId(id).orElse(null);
    }

    public List<CustomersModel> getAll(Map<String, String> filters) {
        return customersRepository.findAll(CustomerSpecification.columnEqual(filters));
    }

    public ValidationDTO create(CustomersModel customer) {
        ValidationDTO validationResponse = checkIfCustomerAlreadyExist(customer);
        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }
        customersRepository.save(customer);
        return new ValidationDTO(true, CustomersRequestStatus.SUCCESS_CREATED.getMessage());
    }

    public ValidationDTO update(Long id, CustomersModel customer) {
        Optional<CustomersModel> optCustomer = customersRepository.findByDeletedIsFalseAndId(id);
        if (optCustomer.isEmpty()) {
            return new ValidationDTO(false, CustomersRequestStatus.CUSTOMER_NOT_FOUND.getMessage());
        }

        if (!checkIfCustomerAlreadyExist(customer).isSuccess()) {
            return new ValidationDTO(false, CustomersRequestStatus.CUSTOMER_ALREADY_EXIST.getMessage());
        }

        CustomersModel currentCustomer = optCustomer.get();

        currentCustomer.setCpf(customer.getCpf());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setName(customer.getName());
        currentCustomer.setPhone(customer.getPhone());
        customersRepository.save(currentCustomer);

        return new ValidationDTO(true, CustomersRequestStatus.CUSTOMER_UPDATED.getMessage());
    }

    public ValidationDTO delete(Long id) {
        Optional<CustomersModel> optCustomer = customersRepository.findByDeletedIsFalseAndId(id);
        if (optCustomer.isEmpty()) {
            return new ValidationDTO(false, CustomersRequestStatus.CUSTOMER_NOT_FOUND.getMessage());
        }
        customersRepository.delete(optCustomer.get());
        return new ValidationDTO(true, CustomersRequestStatus.DELETED.getMessage());
    }

    public ValidationDTO checkIfCustomerAlreadyExist(CustomersModel customer) {
        Optional<CustomersModel> optCustomer = customersRepository.findByCpf(customer.getCpf());
        if (optCustomer.isPresent() && !optCustomer.get().getId().equals(customer.getId())) {
            return new ValidationDTO(false, CustomersRequestStatus.CUSTOMER_ALREADY_EXIST.getMessage());
        }
        return new ValidationDTO(true, "");
    }
}
