package com.example.cloudfilestorage.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class FileUploadDto {

    private MultipartFile file;
}
