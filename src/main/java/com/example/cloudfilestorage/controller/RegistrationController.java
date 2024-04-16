package com.example.cloudfilestorage.controller;


import com.example.cloudfilestorage.dto.UserDTO;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main")
@Log4j2
public class RegistrationController {
    private final UserService userService;
    @PostMapping("/registration")
    public String registrationNewUser(@Valid @ModelAttribute("user") UserDTO userDTO,
                                                 BindingResult bindingResult)
        throws Exception{
        if(bindingResult.hasErrors()){
        }else {
            User user = this.userService.registerNewUser(userDTO);

        }
        return "redirect:/api/main";
    }

}
