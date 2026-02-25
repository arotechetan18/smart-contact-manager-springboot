package com.SCM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SCM.entities.ContactMessage;
import com.SCM.repositories.ContactMessageRepository;

@Controller
@RequestMapping("/contact")
public class PublicContactController {

    @Autowired
    private ContactMessageRepository contactRepo;
    
        @GetMapping
    public String contactPage() {
        return "contact";
    }

    @PostMapping("/send")
    public String sendContactMessage(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message,
            RedirectAttributes redirectAttributes) {

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);

        ContactMessage msg = new ContactMessage();
        

        msg.setName(name);
        msg.setEmail(email);
        msg.setSubject(subject);
        msg.setMessage(message);

        contactRepo.save(msg);

        return "redirect:/contact";
    }
}