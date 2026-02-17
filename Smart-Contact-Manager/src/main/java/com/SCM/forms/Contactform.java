package com.SCM.forms;

import org.springframework.web.multipart.MultipartFile;

import com.SCM.Validaters.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contactform {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Email address")
    private String email;

    @NotBlank(message = "phonenumber is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid Phone number")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    private String description;

private boolean favourite;



private String websiteLink;

private String linkedInLink;

//annotation create for file validate
//size and resoluction

   @ValidFile(message = "invalid file")
    private MultipartFile contactImage;

    private String picture;


}
