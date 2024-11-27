package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.DTO.ValidationDTO;
import com.manageRestaurant.Restaurante.enums.LoginStatus;
import com.manageRestaurant.Restaurante.enums.UsersStatus;
import com.manageRestaurant.Restaurante.models.UsersModel;
import com.manageRestaurant.Restaurante.repositories.UsersRepository;
import com.manageRestaurant.Restaurante.repositories.specifications.UsersSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersModel getById(Long id) {
        return usersRepository.findByDeletedIsFalseAndId(id).orElse(null);
    }

    public List<UsersModel> getAll(Map<String, String> filters) {
        return usersRepository.findAll(UsersSpecification.columnEqual(filters));
    }

    public ValidationDTO create(UsersModel user) {
        ValidationDTO validationResponse = checkIfUserAlreadyExist(user);
        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return new ValidationDTO(true, UsersStatus.SUCCESS.getMessage());
    }

    public ValidationDTO update(Long id, UsersModel user) {
        Optional<UsersModel> optUser = usersRepository.findById(id);
        if (optUser.isEmpty()) {
            return new ValidationDTO(false, UsersStatus.USER_NOT_FOUND.getMessage()/* mensagem aq */);
        }

        ValidationDTO userValidation = checkIfUserAlreadyExist(user);
        if (!userValidation.isSuccess()) {
            return new ValidationDTO(false, userValidation.getMessage());
        }

        UsersModel currentUser = optUser.get();
        currentUser.setName(user.getName());
        currentUser.setCpf(user.getCpf());
        currentUser.setUsername(user.getUsername().toLowerCase());
        currentUser.setRole(user.getRole());
        usersRepository.save(currentUser);
        return new ValidationDTO(true, UsersStatus.SUCCESS.getMessage());
    }

    public ValidationDTO delete(Long id) {
        Optional<UsersModel> optUser = usersRepository.findById(id);
        if (optUser.isEmpty()) {
            return new ValidationDTO(false, UsersStatus.USER_NOT_FOUND.getMessage());
        }
        usersRepository.delete(optUser.get());
        return new ValidationDTO(true, UsersStatus.SUCCESS_DELETED.getMessage());
    }

    public ValidationDTO authenticateUserCredentials(String username, String password) {
        Optional<UsersModel> userInstance = usersRepository.findByUsername(username.toLowerCase());
        if (userInstance.isPresent()) {
            UsersModel findedUser = userInstance.get();
            Boolean isValid = passwordEncoder.matches(password, findedUser.getPassword());
            String message = isValid ? LoginStatus.IS_VALID.getMessage() : LoginStatus.INVALID_PASSWORD.getMessage();
            return new ValidationDTO(isValid, message);
        }
        return new ValidationDTO(false, LoginStatus.USER_NOT_FOUND.getMessage());
    }

    public ValidationDTO checkIfUserAlreadyExist(UsersModel user) {
        Optional<UsersModel> optUsersUsername = usersRepository.findByUsername(user.getUsername().toLowerCase());
        if (optUsersUsername.isPresent() && !optUsersUsername.get().getId().equals(user.getId())) {
            return new ValidationDTO(false, UsersStatus.USERNAME_ALREADY_EXISTS.getMessage());
        }
        Optional<UsersModel> optUsersCpf = usersRepository.findByCpf(user.getCpf().toLowerCase());
        if (optUsersCpf.isPresent() && !optUsersCpf.get().getId().equals(user.getId())) {
            return new ValidationDTO(false, UsersStatus.CPF_ALREADY_EXISTS.getMessage());
        }
        return new ValidationDTO(true, "");
    }

}
