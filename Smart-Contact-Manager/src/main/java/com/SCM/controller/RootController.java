package com.SCM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;    
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.SCM.entities.User;
import com.SCM.helpers.Helper;
import com.SCM.services.UserService;

// Global controller advice to add logged-in user details to all views
@ControllerAdvice
public class RootController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserService userService;

    // Automatically adds logged-in user information to every view model
    @ModelAttribute
    public void addLoginUserInformation(Model model, Authentication authentication) {

           if (authentication == null) {
            return;
        }
        System.out.println("adding log in imnformation in the model");
        String username = Helper.getEmailofLoggedinUser(authentication);

        User user = userService.getUserByEmail(username);

        
        System.out.println(user.getName());

        logger.info("user logged in:{}" + username);

        // Add logged-in user object to model
        model.addAttribute("loggedInUser", user);

    }

}
