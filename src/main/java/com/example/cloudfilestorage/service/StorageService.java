package com.example.cloudfilestorage.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface StorageService {

    void createInitialBucketForUser(String username) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    void uploadFile(String path, MultipartFile multipartFile);
    void createDirectory(String directoryName);


}
