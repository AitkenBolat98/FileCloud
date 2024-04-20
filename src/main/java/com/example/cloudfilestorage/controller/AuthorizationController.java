package com.example.cloudfilestorage.controller;

import com.example.cloudfilestorage.dto.UserAuthorizationDTO;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main/login")
@Log4j2
public class AuthorizationController {
    private final UserService userService;
    @GetMapping
    public String getAuthorizationPage(Model model){
        model.addAttribute("authorization",new UserAuthorizationDTO());
        model.addAttribute("authorizationValidationError","");
        model.addAttribute("authorizationUserError","");
        return "authorization";
    }

    @PostMapping
    private String login(@Valid UserAuthorizationDTO authorizationDTO,
                         BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("authorizationValidationError","VALIDATION ERROR");
        }else {
            if(userService.isUserExist(authorizationDTO.getUsername())){
                User user = this.userService.login(authorizationDTO);
                model.addAttribute("authorization",authorizationDTO);
            }else {
                model.addAttribute("authorizationUserError","USER DOES NOT EXIST");
            }

        }
        return "redirect:/api/main";
    }
}
