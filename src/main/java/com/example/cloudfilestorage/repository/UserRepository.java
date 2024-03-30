package com.example.cloudfilestorage.repository;

import com.example.cloudfilestorage.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

}
