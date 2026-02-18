package com.SCM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.entities.User;
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

   @GetMapping("/profile")
public String userProfile(Model model, Authentication authentication) {

    String email = authentication.getName();

    User user = userService.getUserByEmail(email);

    model.addAttribute("user", user);

    return "user/profile";
    
}


 

    // show edit profile form
    @GetMapping("/edit-profile")
    public String editProfileForm(Model model, Authentication auth) {
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "user/edit-profile";
    }

    // handle edit profile submission
    @PostMapping("/edit-profile")
    public String updateProfile(@ModelAttribute("user") User user, Authentication auth, Model model) {
        String email = auth.getName();
        User existingUser = userService.getUserByEmail(email);
        existingUser.setName(user.getName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAbout(user.getAbout());
        userService.updateUser(existingUser);

        return "redirect:/user/profile";
    }
}
