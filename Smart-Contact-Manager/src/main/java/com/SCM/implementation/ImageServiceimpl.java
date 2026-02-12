package com.SCM.implementation;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SCM.helpers.AppConstant;
import com.SCM.services.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageServiceimpl implements ImageService {

    private Cloudinary cloudinary;

    public ImageServiceimpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage,String filename) {

        // code that image upload on the server and return url

        String filename1 = UUID.randomUUID().toString();

        try {
            byte[] data = new byte[contactImage.getInputStream().available()];

            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", filename1));

            return this.getUrlFromPublicId(filename1);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

             return null;
        }

       

    }

    @Override
    public String getUrlFromPublicId(String publicId) {

        return cloudinary
                .url()
                .transformation(
                        new Transformation<>()
                                .width(AppConstant.CONTACT_IMAGE_WIDTH)
                                .height(AppConstant.CONTACT_IMAGE_HEIGHT)
                                .crop(AppConstant.CONTACT_IMAGE_CROP))
                .generate(publicId);
    }

}
