package com.example.cloudfilestorage.configuration;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
    private final String endPoint = "https://play.min.io";
    private final String accessKey = "cloud";

    private final String secretKey = "123456789";
    @Bean
    public MinioClient minioClient(){
        return MinioClient
                .builder()
                .endpoint(endPoint)
                .credentials(accessKey,secretKey)
                .build();
    }
}
