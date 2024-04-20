package com.image.upload.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.image.upload.model.Image;
import com.image.upload.repository.ImageRepository;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;


@RestController
public class ImageController {

    @Autowired
    private MongoTemplate mongoTemplate; // Autowire MongoTemplate
    @Autowired
    private ImageRepository imageRepository; // Assuming you have a repository for managing images

    @GetMapping("/images")
    public List<Image> getAllImages() {
        return imageRepository.findAll(); // Retrieve all images from MongoDB
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            // Save file to MongoDB
            // Example: mongoRepository.save(file.getBytes());
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                assert fileName != null;
                byte[] imageData = file.getBytes();
                // Save image to MongoDB
                mongoTemplate.save(new Image(fileName, imageData), "images");
                return "Image uploaded successfully";
            } else {
                return "No image uploaded";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading image";
        }
    }
    @DeleteMapping("{id}")
    public void deleteImages(@PathVariable("id") String id) {
    	imageRepository.deleteById(id);
    }
}

