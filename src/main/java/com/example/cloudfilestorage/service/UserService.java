package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.dto.UserAuthorizationDTO;
import com.example.cloudfilestorage.dto.UserRegistrationDTO;
import com.example.cloudfilestorage.module.User;

import java.util.Optional;

public interface UserService {
    User registerNewUser(UserRegistrationDTO userDTO);
    boolean isUserExist(String username);

    User login(UserAuthorizationDTO dto);


    User getUserByUsername(String username);

}
