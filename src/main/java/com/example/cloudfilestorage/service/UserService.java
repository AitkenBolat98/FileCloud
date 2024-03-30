package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.dto.UserDTO;
import com.example.cloudfilestorage.module.User;

public interface UserService {
    User registerNewUser(UserDTO userDTO);
}
