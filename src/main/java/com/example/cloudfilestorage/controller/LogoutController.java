package com.example.cloudfilestorage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main/logout")
@Log4j2
public class LogoutController {
    @GetMapping
    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/api/main";
    }
}
