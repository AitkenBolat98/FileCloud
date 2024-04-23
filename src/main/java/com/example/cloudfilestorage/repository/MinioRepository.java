package com.example.cloudfilestorage.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface MinioRepository {

    void createBucket();

    void uploadFile();
}
