package com.manageRestaurant.Restaurante.repositories.mappers;

import com.manageRestaurant.Restaurante.DTO.Users.UsersPutRequest;
import com.manageRestaurant.Restaurante.DTO.Users.UsersRequest;
import com.manageRestaurant.Restaurante.DTO.Users.UsersResponse;
import com.manageRestaurant.Restaurante.models.UsersModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersMapper {
    public UsersRequest toRequest(UsersModel user) {
        UsersRequest userRequest = new UsersRequest();
        userRequest.setCpf(user.getCpf());
        userRequest.setRole(user.getRole());
        userRequest.setName(user.getName());
        userRequest.setUsername(user.getUsername());
        userRequest.setPassword(user.getPassword());
        return userRequest;
    }

    public UsersModel toModel(UsersRequest userRequest) {
        UsersModel usersModel = new UsersModel();
        usersModel.setCpf(userRequest.getCpf());
        usersModel.setRole(userRequest.getRole());
        usersModel.setName(userRequest.getName());
        usersModel.setUsername(userRequest.getUsername());
        usersModel.setPassword(userRequest.getPassword());
        return usersModel;
    }

    public UsersModel toModel(UsersPutRequest userPutRequest) {
        UsersModel usersModel = new UsersModel();
        usersModel.setCpf(userPutRequest.getCpf());
        usersModel.setRole(userPutRequest.getRole());
        usersModel.setName(userPutRequest.getName());
        usersModel.setUsername(userPutRequest.getUsername());
        return usersModel;
    }

    public UsersResponse toResponse(UsersModel usersModel) {
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setId(usersModel.getId());
        usersResponse.setCpf(usersModel.getCpf());
        usersResponse.setRole(usersModel.getRole());
        usersResponse.setName(usersModel.getName());
        usersResponse.setUsername(usersModel.getUsername());
        return usersResponse;
    }

    public List<UsersResponse> toResponseList(List<UsersModel> list) {
        return list.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
