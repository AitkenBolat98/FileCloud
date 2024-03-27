package com.example.cloudfilestorage.dto;

import org.antlr.v4.runtime.misc.NotNull;

public class UserDTO {
    @NotNull
    private String firstName;

    @NotNull

    private String lastName;

    @NotNull
    private String password;
    private String matchingPassword;

    @NotNull
    private String email;

}
