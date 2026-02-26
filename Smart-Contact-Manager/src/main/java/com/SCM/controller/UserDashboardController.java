package com.SCM.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String userDashboard(Model model, Principal principal) {

        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        // total contacts
        long totalContacts = contactService.countByUser(user);

        // favourite contacts
        long favouriteContacts = contactService.countFavouriteByUser(user);

        // recent 5 contacts
        List<Contact> recentContacts = contactService.getRecentContacts(user);

        model.addAttribute("loggedInUser", user);
        model.addAttribute("totalContacts", totalContacts);
        model.addAttribute("favouriteContacts", favouriteContacts);
        model.addAttribute("recentContacts", recentContacts);

        return "user/dashboard";
    }
}