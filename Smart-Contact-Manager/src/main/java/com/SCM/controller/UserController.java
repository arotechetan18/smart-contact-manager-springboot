package com.SCM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard
    @RequestMapping("/dashboard")
    public String userDashboard(){

        return "user/dashboard";
    }
    //user profile page

     @RequestMapping("/profile")
    public String userProfile(){

        return "user/profile";
    }

    //user contacts page

    //user view contact details

    //user edit contact details

    //user delete contact

    //user serch contacts
    


}
