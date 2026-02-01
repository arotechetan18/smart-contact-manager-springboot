package com.SCM.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

  private Logger logger = LoggerFactory.getLogger(UserController.class);
    //user dashboard
    @RequestMapping("/dashboard")
    public String userDashboard(){
        

        return "user/dashboard";
    }
    //user profile page

     @RequestMapping("/profile")
    public String userProfile(Authentication authentication){
String username=Helper.getEmailofLoggedinUser(authentication);
logger.info("user logged in:{}"+username);
        return "user/profile";



    }

    //user contacts page

    //user view contact details

    //user edit contact details

    //user delete contact

    //user serch contacts
    


}
