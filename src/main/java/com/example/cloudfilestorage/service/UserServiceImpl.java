package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.dto.UserAuthorizationDTO;
import com.example.cloudfilestorage.dto.UserRegistrationDTO;
import com.example.cloudfilestorage.module.Authority;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.repository.AuthorityRepository;
import com.example.cloudfilestorage.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    @Override
    public User registerNewUser(UserRegistrationDTO userDTO) {
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
    @Override
    public boolean isUserExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public User login(UserAuthorizationDTO dto) {
        User user = getUserByUsername(dto.getUsername());
        String password = user.getPassword();
        if(isPasswordValid(dto.getPassword(),password)){
            return user;
        }else {
            throw new RuntimeException("Invalid Password");
        }

    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    private boolean isPasswordValid(String enteredPassword, String originalPassword){
        return BCrypt.checkpw(enteredPassword,originalPassword);

    }

}
