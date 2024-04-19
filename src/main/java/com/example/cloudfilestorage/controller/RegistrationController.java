package com.example.cloudfilestorage.controller;


import com.example.cloudfilestorage.dto.UserRegistrationDTO;
import com.example.cloudfilestorage.module.User;
import com.example.cloudfilestorage.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main/registration")
@Log4j2
public class RegistrationController {
    private final UserService userService;
    @PostMapping()
    public String registrationNewUser(@Valid UserRegistrationDTO userDTO,
                                      BindingResult bindingResult,Model model)
        throws Exception{
        System.out.println("ssdsadasdadadasdas");
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationValidationError","VALIDATION ERROR");
        }else {
            if(!userService.isUserExist(userDTO.getUsername())){
                User user = this.userService.registerNewUser(userDTO);
                model.addAttribute("user",userDTO);
            }else {
                model.addAttribute("registrationUserError","USER ALREADY EXIST");
            }

        }
        return "redirect:/api/main";
    }
    @GetMapping()
    public String RegistrationPage(Model model){
        model.addAttribute("user",new UserRegistrationDTO());
        model.addAttribute("registrationUserError","");
        model.addAttribute("registrationValidationError","");
        return "registration";
    }

}
