package com.SCM.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForms {
    @NotBlank(message = "Username required *****")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 50 characters")
    private String name;
    @Email(message = "Invalid email Address *****")
    @NotBlank(message = "Email is required *****")
    private String email;
    @NotBlank(message = "Password required *****")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password; 
    @NotBlank(message = "About is required *****")   
    private String about;
    @NotBlank(message = "Phone number is required *****")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;
}
