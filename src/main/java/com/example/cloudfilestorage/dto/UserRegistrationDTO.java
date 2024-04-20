package com.example.cloudfilestorage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
