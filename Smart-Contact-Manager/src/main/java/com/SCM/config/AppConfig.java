package com.SCM.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

// Configuration class to set up Cloudinary bean for image upload
@Configuration
public class AppConfig {
    // name
    @Value("${cloudinary.cloud.name}")
    public String CloudName;

    // key
    @Value("${cloudinary.api.key}")
    private String apiKey;
    
     //apisecret
    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {

        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", CloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret));

    }

}
