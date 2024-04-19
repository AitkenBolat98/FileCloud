package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.dto.UserAuthorizationDTO;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public User getUser(UserAuthorizationDTO dto) {
        Optional<User> user = userRepository.findByUsername(dto.getUsername());
        String password = user.map(User::getPassword).orElse(null);

}
