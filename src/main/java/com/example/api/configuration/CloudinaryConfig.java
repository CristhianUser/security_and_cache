package com.example.api.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.api.key}")
    private String cloudinaryKey;

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(cloudinaryKey);
    }

}
