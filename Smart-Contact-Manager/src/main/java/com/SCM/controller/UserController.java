package com.SCM.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.entities.User;
import com.SCM.helpers.Helper;
import com.SCM.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;







  private Logger logger = LoggerFactory.getLogger(UserController.class);
    //user dashboard
    @RequestMapping("/dashboard")
    public String userDashboard(){
        

        return "user/dashboard";
    }
    //user profile page

     @RequestMapping("/profile")
    public String userProfile(Authentication authentication,Model model){

        return "user/profile";





    }

    //user contacts page

    //user view contact details

    //user edit contact details

    //user delete contact

    //user serch contacts
    


}
