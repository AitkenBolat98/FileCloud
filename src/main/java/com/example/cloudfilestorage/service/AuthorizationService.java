package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.dto.UserAuthorizationDTO;
import com.example.cloudfilestorage.module.User;

public interface AuthorizationService {

    User getUser(UserAuthorizationDTO dto);


}
