package com.SCM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SCM.entities.Contact;
import com.SCM.services.ContactService;
import com.SCM.services.EmailService;

@Controller
@RequestMapping("/user")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ContactService contactService;

    // Open Email Form
    @GetMapping("/send-email/{contactId}")
    public String openEmailForm(@PathVariable String contactId, Model model) {

        Contact contact = contactService.getbyid(contactId);
        model.addAttribute("contact", contact);

        return "user/send_email";
    }

    // Send Email
    @PostMapping("/send-email")
    public String sendEmailToContact(
            @RequestParam String contactId,
            @RequestParam String subject,
            @RequestParam String message) {

        Contact contact = contactService.getbyid(contactId);

        emailService.sendEmail(
                contact.getEmail(),
                subject,
                message
        );

        return "redirect:/user/contacts";
    }
}