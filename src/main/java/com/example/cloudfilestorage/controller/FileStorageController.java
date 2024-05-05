package com.example.cloudfilestorage.controller;

import com.example.cloudfilestorage.dto.FileUploadDto;
import com.example.cloudfilestorage.service.MinioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main/storage")
@Log4j2
public class FileStorageController {

    private final MinioService minioService;

    @GetMapping()
    public String getStoragePage(Model model){
        return "storage";
    }
    @PostMapping("/upload")
    public String uploadFile(FileUploadDto fileUploadDto, HttpSession session){
        MultipartFile file = fileUploadDto.getFile();
        minioService.uploadFile(session.get);

    }
    private static String getPath(HttpSession session){
        return (String) session.getAttribute("path");
    }

}
