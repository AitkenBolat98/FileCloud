package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userDetails -> new User(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities())).orElseThrow(
                                ()-> new UsernameNotFoundException("Failed To Retrieve Username " + username));

    }
}
