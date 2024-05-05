package com.example.cloudfilestorage.service;

import com.example.cloudfilestorage.properties.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService{

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    @Override
    @PostConstruct
    public void createAppBucket() {
        try {
            if(!minioClient.bucketExists(BucketExistsArgs
                    .builder()
                    .bucket(minioProperties.getBucket())
                    .build())){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket())
                        .build());
            }
        }catch (Exception e){
            throw new RuntimeException("Create App Bucket Exception " + e);
        }
    }

    private void createBucket(String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean exist = minioClient.bucketExists(BucketExistsArgs
                .builder()
                .bucket(minioProperties.getBucket())
                .build());
        if(!exist){
            minioClient.makeBucket(MakeBucketArgs
                    .builder()
                    .bucket(path)
                    .build());
        }


    }

    @Override
    public void uploadFile(String path, @NotNull MultipartFile multipartFile) {
        try {
            createBucket(path);
        }catch (Exception e){
            throw new RuntimeException("create bucket Exception");
        }

        if(multipartFile.isEmpty() || multipartFile.getOriginalFilename() == null){
            throw new RuntimeException("file is empty/null ");
        }
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        }catch (Exception e){
            throw new RuntimeException("inputstream Exception");
        }
        saveFile(inputStream,path);
    }
    @SneakyThrows
    private void saveFile(InputStream stream,String path){
        minioClient.putObject(PutObjectArgs
                .builder()
                .stream(stream, stream.available(),-1)
                .bucket(minioProperties.getBucket())
                .object(path)
                .build());
    }

}
