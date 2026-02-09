package com.SCM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    //add contact page
    @RequestMapping("/add")
    public String addContactView(){

        return "user/add_contact";
    }

}
