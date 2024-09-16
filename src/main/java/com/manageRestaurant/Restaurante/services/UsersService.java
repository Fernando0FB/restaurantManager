package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.models.UsersModel;
import com.manageRestaurant.Restaurante.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<Boolean, String> createUser(UsersModel user) {
        Map<Boolean, String> response = new HashMap();

        Map<Boolean, String> alreadyExist = checkIfUserAlreadyExist(user);
        Boolean exist = alreadyExist.keySet().iterator().next();
        if (exist) {
            String message = alreadyExist.get(exist);
            response.put(false, message);
            return response;
        }
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        response.put(true, "");
        return response;
    }

    public Map<Boolean, String> authenticateUserCredentials(String username, String password) {
        Map response = new HashMap();
        Optional<UsersModel> userInstance = usersRepository.findByUsername(username);
        if (userInstance.isPresent()) {
            UsersModel findedUser = userInstance.get();
            Boolean isValid = passwordEncoder.matches(password, findedUser.getPassword());
            String message = isValid ? "" : "Senha incorreta!";
            response.put(isValid, message);
            return response;
        }
        response.put(false, "Usuário não encontrado!");
        return response;
    }

    private Map<Boolean, String> checkIfUserAlreadyExist(UsersModel user) {
        Map<Boolean, String> response = new HashMap<>();
        Optional<UsersModel> userInstanceByUsername = usersRepository.findByUsername(user.getUsername().toLowerCase());
        if (userInstanceByUsername.isPresent()) {
            response.put(true, "Já existe um usuário cadastrado com esse username!");
            return response;
        }
        Optional<UsersModel> userInstanceByCpf = usersRepository.findByCpf(user.getCpf().toLowerCase());
        if (userInstanceByCpf.isPresent()) {
            response.put(true, "Já existe um usuário cadastrado com esse CPF!");
            return response;
        }
        response.put(false, "");
        return response;
    }

}
