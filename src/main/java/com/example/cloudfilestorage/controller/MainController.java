package com.example.cloudfilestorage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main")
@Log4j2
public class MainController {

    @GetMapping
    public String getMainPage(){
        return "main";
    }

}
