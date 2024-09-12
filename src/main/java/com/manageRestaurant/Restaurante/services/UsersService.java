package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.models.Users;
import com.manageRestaurant.Restaurante.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Map<Boolean, String> createUser(Users user) {
        Map<Boolean, String> response = new HashMap();
        Optional<Users> userInstanceByUsername = usersRepository.findByUsername(user.getUsername().toLowerCase());
        if(userInstanceByUsername.isPresent()){
            response.put(false, "Já existe um usuário cadastrado com esse username!");
            return response;
        }
        Optional<Users> userInstanceByCpf = usersRepository.findByCpf(user.getCpf().toLowerCase());
        if(userInstanceByCpf.isPresent()){
            response.put(false, "Já existe um usuário cadastrado com esse CPF!");
            return response;
        }
        user.setUsername(user.getUsername().toLowerCase());
        usersRepository.save(user);
        response.put(true, "");
        return response;
    }

    public Map<Boolean, String> authenticateUserCredentials(String username, String password) {
        Map response = new HashMap();
        Optional<Users> userInstance = usersRepository.findByUsername(username);
        if(userInstance.isPresent()){
            Users findedUser = userInstance.get();
            Boolean isValid = findedUser.getPassword().equals(password.toLowerCase());
            String returnMessage = isValid ? "" : "Senha incorreta!";
            response.put(isValid, returnMessage);
            return response;
        }
        response.put(false, "Usuário não encontrado!");
        return response;
    }

}
