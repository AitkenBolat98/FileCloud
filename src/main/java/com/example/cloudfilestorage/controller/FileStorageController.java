package com.example.cloudfilestorage.controller;

import com.example.cloudfilestorage.dto.FileUploadDto;
import com.example.cloudfilestorage.service.MinioService;
import com.example.cloudfilestorage.service.StorageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main/storage/")
@Log4j2
public class FileStorageController {

    private final StorageService storageService;
    @GetMapping()
    public String getStoragePage(Model model){
        return "storage";
    }
   @PostMapping("uploadFile")
    public String uploadFile(FileUploadDto fileUploadDto, Principal principal){
        String username = principal.getName();
    }
    @PostMapping("createDirectory")
    public String createDirectory(){

    }



}
