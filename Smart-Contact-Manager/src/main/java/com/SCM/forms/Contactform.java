package com.SCM.forms;

import org.springframework.web.multipart.MultipartFile;

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

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String description;

private boolean favourite;



private String websiteLink;

private String linkedInLink;


    private MultipartFile profileImage;


}
