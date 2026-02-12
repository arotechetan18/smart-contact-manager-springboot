package com.SCM.Validaters;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile,MultipartFile>{

    private static final long MAX_FILE_SIZE =1024 * 1024 * 5;
    private BufferedImage bufferedImage;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
       
        if(file==null || file.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File cannot be Empty").addConstraintViolation();
            return false;

        }

        //filesize
        if(file.getSize()>MAX_FILE_SIZE){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("file size should be less than 2MB").addConstraintViolation();
            return false;
        }

        //resoluction
        // try {
        //  BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

        //  if(bufferedImage.getHeight())
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        
        return true;
    
    }

}
