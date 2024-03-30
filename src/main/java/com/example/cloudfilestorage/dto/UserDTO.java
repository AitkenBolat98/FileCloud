package com.example.cloudfilestorage.dto;

import lombok.Getter;

@Getter
public class UserDTO {
    private String firstName;

    private String lastName;

    private String password;
    private String matchingPassword;

    private String email;

}
