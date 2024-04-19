package com.example.cloudfilestorage.controller;

import com.example.cloudfilestorage.dto.UserAuthorizationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main/authorization")
@Log4j2
public class AuthorizationController {
    @GetMapping
    public String getAuthorizationPage(Model model){
        model.addAttribute("authorization",new UserAuthorizationDTO());
        model.addAttribute("authorizationValidationError","");
        model.addAttribute("authorizationUserError","");
        return "authorization";
    }
}
