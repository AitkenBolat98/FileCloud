package com.example.cloudfilestorage.service;

import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService{

    private final MinioClient minioClient;
    @Override
    public void createBucket() {
        minioClient.makeBucket(MakeBucketArgs
                .builder()
                        .bucket()
                .build());

    }

    @Override
    public void uploadFile() {

    }
}
