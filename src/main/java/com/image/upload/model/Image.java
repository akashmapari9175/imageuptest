package com.image.upload.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    
    @Id
    private String id;
    private String name;
    private byte[] data;

    public Image(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
    

    // Getters and setters
}
