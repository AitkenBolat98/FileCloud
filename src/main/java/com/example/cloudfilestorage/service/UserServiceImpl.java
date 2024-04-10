package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.configuration.SecurityConfiguration;
import com.example.cloudfilestorage.dto.UserDTO;
import com.example.cloudfilestorage.module.Authority;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.repository.AuthorityRepository;
import com.example.cloudfilestorage.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    @Override
    public User registerNewUser(UserDTO userDTO) {
        User user = new User();
        authorityRepository.findAuthorityByName("USER");
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setAuthorities();
        return userRepository.save(user);
    }

}
