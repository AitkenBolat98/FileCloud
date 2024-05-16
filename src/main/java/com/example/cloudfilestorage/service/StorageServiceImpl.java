package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.module.User;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Formatter;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final MinioService minioService;
    private final UserService userService;
    @Override
    public void createInitialBucketForUser(String userName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        User user = userService.getUserByUsername(userName);
        String userId = user.getId().toString();
        String path ="user-" + userId + "-files";
        minioService.createBucket(path);
    }


    @Override
    public void uploadFile(String fileName,String directoryName,String userName, MultipartFile multipartFile) {
        User user = userService.getUserByUsername(userName);
        String userId = encodeUserId(user.getId());
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
    private String encodeUserId(Integer userId) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(userId.toString().getBytes());
            return byteArrayToHex(hash).substring(0, 16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding user ID", e);
        }
    }

    private String byteArrayToHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}

