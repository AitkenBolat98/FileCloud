package com.example.cloudfilestorage.service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService{

    private final MinioClient minioClient;

    @Override
    @PostConstruct
    public void createAppBucket() {
        try {
            if(!minioClient.bucketExists(BucketExistsArgs
                    .builder()
                    .bucket("cloudFileStorage")
                    .build())){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("cloudFileStorage")
                        .build());
            }
        }catch (Exception e){
            throw new RuntimeException("Create App Bucket Exception " + e);
        }
    }

    @Override
    public void createBucket(String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.makeBucket(MakeBucketArgs
                .builder()
                        .bucket(path)
                .build());

    }

    @Override
    public void uploadFile() {

    }
}
