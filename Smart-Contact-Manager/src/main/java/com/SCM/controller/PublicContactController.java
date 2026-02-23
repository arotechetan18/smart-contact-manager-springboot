package com.SCM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contact")
public class PublicContactController {

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

        try {


            redirectAttributes.addFlashAttribute("success", true);

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", true);
        }

        return "redirect:/contact";
    }

}