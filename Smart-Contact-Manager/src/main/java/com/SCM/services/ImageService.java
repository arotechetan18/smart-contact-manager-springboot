package com.SCM.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    //upload image and return url
    String uploadImage(MultipartFile contactImage ,String filename);

    String getUrlFromPublicId(String publicId);


}
