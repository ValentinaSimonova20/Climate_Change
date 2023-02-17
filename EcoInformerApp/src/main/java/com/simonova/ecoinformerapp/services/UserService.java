package com.simonova.ecoinformerapp.services;

import com.simonova.ecoinformerapp.models.User;
import com.simonova.ecoinformerapp.models.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
