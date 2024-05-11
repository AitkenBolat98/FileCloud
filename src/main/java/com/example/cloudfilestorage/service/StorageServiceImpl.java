package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.module.User;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final MinioService minioService;
    private final UserService userService;
    @Override
    public void createInitialBucketForUser(String userName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        User user = userService.getUserByUsername(userName);
        String userId = Base64.getEncoder().encodeToString(String.valueOf(user.getId()).getBytes());
        String path ="user-" + userId + "-files";
        minioService.createBucket(path);
    }


    @Override
    public void uploadFile(String fileName,String directoryName,String userName, MultipartFile multipartFile) {
        User user = userService.getUserByUsername(userName);
        String userId = Base64.getEncoder().encodeToString(String.valueOf(user.getId()).getBytes());
        String userPath = "user-" + userId + "-files";
        String directoryPath = userPath + "/" + directoryName;
        String filename = directoryPath + "/" + fileName;
        minioService.uploadFile(fileName,multipartFile);

    }

    @Override
    public void createDirectory(String directoryName,String userName) {
        User user = userService.getUserByUsername(userName);
        String userId = Base64.getEncoder().encodeToString(String.valueOf(user.getId()).getBytes());
        String userPath = "user-" + userId + "-files";
        String directoryPath = userPath + "/" + directoryName;
        minioService.createMinioDirectory(directoryPath);
    }
}
