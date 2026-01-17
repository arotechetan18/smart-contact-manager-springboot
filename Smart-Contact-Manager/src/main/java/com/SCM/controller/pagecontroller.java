package com.SCM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pagecontroller {
    
    @RequestMapping("/home")
    public String home(){
System.out.println("homepage handler");
        return "home";
    }
}
