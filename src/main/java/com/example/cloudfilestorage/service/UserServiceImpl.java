package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.dto.UserDTO;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User registerNewUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.USER);
        user.setPassword(user.getPassword());

        return userRepository.save(user);
    }

}
