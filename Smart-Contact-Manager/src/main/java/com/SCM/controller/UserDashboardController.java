package com.SCM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SCM.entities.Contact;
import com.SCM.entities.User;
import com.SCM.services.ContactService;
import com.SCM.services.UserService;

@Controller
public class UserDashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;
@GetMapping("/user/dashboard")
public String userDashboard(Model model, Authentication authentication) {

    Object principal = authentication.getPrincipal();
    String email = null;

    if (principal instanceof OAuth2User oauthUser) {
        email = oauthUser.getAttribute("email");
    } else if (principal instanceof org.springframework.security.core.userdetails.User userDetails) {
        email = userDetails.getUsername();
    }

    User user = userService.getUserByEmail(email);

    long totalContacts = contactService.countByUser(user);
    long favouriteContacts = contactService.countFavouriteByUser(user);
    List<Contact> recentContacts = contactService.getRecentContacts(user);

    model.addAttribute("loggedInUser", user);
    model.addAttribute("totalContacts", totalContacts);
    model.addAttribute("favouriteContacts", favouriteContacts);
    model.addAttribute("recentContacts", recentContacts);

    return "user/dashboard";
}
}