package com.example.cloudfilestorage.repository;

import com.example.cloudfilestorage.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<UserDetails> findByUsername(String username);
}
