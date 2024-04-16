package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.configuration.SecurityConfiguration;
import com.example.cloudfilestorage.dto.UserDTO;
import com.example.cloudfilestorage.module.Authority;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.repository.AuthorityRepository;
import com.example.cloudfilestorage.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    @Override
    public User registerNewUser(UserDTO userDTO) {
        User user = new User();
        List<Authority> authorityList = new ArrayList<>();
        try {
            Authority authority = authorityRepository.findAuthorityByName("USER");
            authorityList.add(authority);
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setAuthorities(authorityList);
        }catch (Exception e){
            log.error("registerNewUser Exception " + e);
        }

        return userRepository.save(user);
    }

}
