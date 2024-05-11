package com.example.cloudfilestorage.configuration;

import com.example.cloudfilestorage.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
@RequiredArgsConstructor
public class MinioConfiguration {
    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient(){
        return MinioClient
                .builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
