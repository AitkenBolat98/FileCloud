package com.example.cloudfilestorage.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MinioService {

    void createAppBucket();



    void uploadFile(String path, MultipartFile multipartFile);

}
