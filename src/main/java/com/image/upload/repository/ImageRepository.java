package com.image.upload.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.image.upload.model.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    // You can define custom query methods here if needed
}

