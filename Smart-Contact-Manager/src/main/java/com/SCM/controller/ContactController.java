package com.SCM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.entities.Contact;
import com.SCM.entities.User;
import com.SCM.forms.Contactform;
import com.SCM.helpers.Helper;
import com.SCM.services.ContactService;
import com.SCM.services.UserService;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    
    @Autowired
    public ContactService contactService;

    @Autowired
    private UserService userService;

    //add contact page
    @RequestMapping("/add")
    public String addContactView(Model model){

   Contactform contactForm = new Contactform();
   
  
model.addAttribute("contactForm", contactForm);


        return "user/add_contact";
    }
@PostMapping("/add")

public String saveContact(@ModelAttribute("contactForm") Contactform contactForm,Authentication authentication) {
//process the form data

String username=Helper.getEmailofLoggedinUser(authentication);

// form covert into contact

  User user=   userService.getUserByEmail(username);



Contact contact=new Contact();
contact.setName(contactForm.getName());
contact.setFavourite(contactForm.isFavourite());
contact.setEmail(contactForm.getEmail());
contact.setPhoneNumber(contactForm.getPhoneNumber());
contact.setAddress(contactForm.getAddress());
contact.setDescription(contactForm.getDescription());
contact.setUser(user);
contact.setWebsiteLink(contactForm.getWebsiteLink());
contact.setLinkdInLink(contactForm.getLinkedInLink());

 contactService.save(contact);

    System.out.println(contactForm);

    return "redirect:/user/contacts/add";
}


}
