package com.example.cloudfilestorage.controller;

import com.example.cloudfilestorage.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main")
@Log4j2
public class MainController {

    private final UserService userService;

    @GetMapping
    public String getMainPage(Model model,
                              Principal principal,
                              HttpSession session,
                              @RequestParam(value = "path",defaultValue = "",required = false) String path){
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        session.setAttribute("path",path);

        if(principal != null){
            String username = principal.getName();
            if(userService.isUserExist(username)){
                model.addAttribute("isAuthorized",true);
            }
        }
        return "main";
    }

}
