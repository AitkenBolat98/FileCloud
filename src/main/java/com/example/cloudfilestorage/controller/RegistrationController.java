package com.example.cloudfilestorage.controller;

import com.example.cloudfilestorage.dto.UserDTO;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class RegistrationController {
    private final UserService userService;
    @GetMapping("/user/registration")
    public String showRegistrationPage(WebRequest request, Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }
    public ModelAndView registerAccount(@ModelAttribute("user") UserDTO userDTO,
                                        HttpServletRequest request, Errors errors){
        try {
            User registered = userService.registerNewUser(userDTO);
        }catch (Exception e){
            log.error("user registration exception " + e.getMessage());
        }
        return new ModelAndView("successRegister","user",userDTO);

    }

}
