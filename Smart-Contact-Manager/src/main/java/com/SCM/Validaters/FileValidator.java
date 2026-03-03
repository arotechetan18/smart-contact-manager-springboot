package com.SCM.Validaters;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Validates MultipartFile upload and restricts file size to 5MB
public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 5;
    private BufferedImage bufferedImage;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        // If file is null or empty - skip validation (field optional)
        if (file == null || file.isEmpty()) {
            return true;
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less than 5MB")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
