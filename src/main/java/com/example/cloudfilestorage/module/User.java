package com.example.cloudfilestorage.module;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
