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
    @RequestMapping("/about")
    public String about(){
        System.out.println("about page");
        return "about";
    }

    @RequestMapping("/base")
    public String base(){
        System.out.println("about page");
        return "base";
    }

    
        @RequestMapping("/services")
    public String services(){
        System.out.println("about page");
        return "services";
    }

      @RequestMapping("/contact")
    public String contact(){
        System.out.println("about page");
        return "contact";
    }

      @RequestMapping("/login")
    public String login(){
        System.out.println("about page");
        return "login";
    }
      @RequestMapping("/register")
    public String register(){
        System.out.println("about page");
        return "register";
    }

}
