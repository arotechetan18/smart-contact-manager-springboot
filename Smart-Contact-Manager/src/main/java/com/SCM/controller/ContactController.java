package com.SCM.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.entities.Contact;
import com.SCM.entities.User;
import com.SCM.forms.Contactform;
import com.SCM.helpers.Helper;
import com.SCM.helpers.MessageType;
import com.SCM.helpers.message;
import com.SCM.services.ContactService;
import com.SCM.services.ImageService;
import com.SCM.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger= org.slf4j.LoggerFactory.getLogger(ContactController.class);
    
    @Autowired
    private ImageService imageService;

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

public String saveContact(@Valid  @ModelAttribute("contactForm") Contactform contactForm,BindingResult result,
Authentication authentication,HttpSession session,Model model) {
//process the form data
String filename=UUID.randomUUID().toString();

String username=Helper.getEmailofLoggedinUser(authentication);



//validate the form
if(result.hasErrors()){

    User user = userService.getUserByEmail(username);
    model.addAttribute("loggedInUser", user);

    session.setAttribute("message", message.builder()
            .content("please correct the following errors")
            .type(MessageType.red)
            .build());

    return "user/add_contact";
}


// form covert into contact

  User user=   userService.getUserByEmail(username);

  //image processing
//to upload
 String fileURL = imageService.uploadImage(contactForm.getContactImage(),filename);


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
contact.setPicture(fileURL);
contact.setCloudinaryImagePublicId(filename);

 contactService.save(contact);

    System.out.println(contactForm);

    session.setAttribute("message",message.builder()
    .content("You have successfully saved contact.")
    .type(MessageType.green)
    .build()
                         
);

    return "redirect:/user/contacts/add";
}


}
