package com.manageRestaurant.Restaurante.services;

import com.manageRestaurant.Restaurante.models.Users;
import com.manageRestaurant.Restaurante.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UsersRepository usersRepository;

    public boolean authenticate(String token) {
        if (token.startsWith("Basic ")) {
            token = token.substring(6);
        }

        String decodedToken = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);

        String[] tokenParts = decodedToken.split(":");
        if (tokenParts.length != 2) {
            return false; // Token malformado
        }

        String username = tokenParts[0];
        String password = tokenParts[1];

        Optional<Users> userExists = usersRepository.findByUsername(username);

        if(userExists.isPresent()) {
            Users user = userExists.get();
            return user.getPassword().equals(password);
        }

        return false;
    }
}
