package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.enums.LoginStatus;
import com.manageRestaurant.Restaurante.enums.UsersStatus;
import com.manageRestaurant.Restaurante.models.UsersModel;
import com.manageRestaurant.Restaurante.DTO.validationDTO;
import com.manageRestaurant.Restaurante.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public validationDTO createUser(UsersModel user) {
        validationDTO validationResponse = checkIfUserAlreadyExist(user);
        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return new validationDTO(true, UsersStatus.SUCCESS.getMessage());
    }

    public validationDTO authenticateUserCredentials(String username, String password) {
        Optional<UsersModel> userInstance = usersRepository.findByUsername(username);
        if (userInstance.isPresent()) {
            UsersModel findedUser = userInstance.get();
            Boolean isValid = passwordEncoder.matches(password, findedUser.getPassword());
            String message = isValid ? LoginStatus.IS_VALID.getMessage() : LoginStatus.INVALID_PASSWORD.getMessage();
            return new validationDTO(isValid, message);
        }
        return new validationDTO(false, LoginStatus.USER_NOT_FOUND.getMessage());
    }

    public validationDTO checkIfUserAlreadyExist(UsersModel user) {
        if (usersRepository.findByUsername(user.getUsername().toLowerCase()).isPresent()) {
            return new validationDTO(false, UsersStatus.USERNAME_ALREADY_EXISTS.getMessage());
        }
        if (usersRepository.findByCpf(user.getCpf().toLowerCase()).isPresent()) {
            return new validationDTO(false, UsersStatus.CPF_ALREADY_EXISTS.getMessage());
        }
        return new validationDTO(true, "");
    }

}
