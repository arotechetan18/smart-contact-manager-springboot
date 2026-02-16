package com.SCM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SCM.entities.Contact;
import com.SCM.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {
    
//get conatct of user
@Autowired
private ContactService contactService; 

@GetMapping("/contacts/{contactId}")
public Contact getContact(@PathVariable String contactId ){
    return contactService.getbyid(contactId);

}



}
