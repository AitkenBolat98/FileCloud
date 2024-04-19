package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.dto.UserRegistrationDTO;
import com.example.cloudfilestorage.module.User;

public interface UserService {
    User registerNewUser(UserRegistrationDTO userDTO);
    boolean isUserExist(String username);
}
