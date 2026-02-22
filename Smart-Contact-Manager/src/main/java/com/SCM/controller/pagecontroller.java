package com.SCM.controller;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SCM.entities.User;
import com.SCM.forms.UserForms;
import com.SCM.helpers.MessageType;
import com.SCM.helpers.message;
import com.SCM.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class pagecontroller {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    private String index(){
        return "redirect:/home";

    }
    
    @RequestMapping("/home")
    public String home(){

        return "home";
    }
    @RequestMapping("/about")
    public String about(){
      
        return "about";
    }

    @RequestMapping("/base")
    public String base(){
     
        return "base";
    }

    

    
        @RequestMapping("/services")
    public String services(){

        return "services";
    }

      @RequestMapping("/contact")
    public String contact(){
        
        return "contact";
    }

    // do process user registration




    //this is show login page
      @RequestMapping("/login")
    public String login(){
      
        return "login";
    }
      @RequestMapping("/register")
    public String register( Model model){
        UserForms UserForms = new UserForms();
       
      
         model.addAttribute("userForm",UserForms); 
      
        return "register";
    }


    //processing register form data

    @RequestMapping(value = "do-register",method = RequestMethod.POST)
   public String processResister(
    @Valid @ModelAttribute("userForm") UserForms userForms,
    BindingResult rBindingResult,
    Model model,
    HttpSession session)
{
        System.out.println("processing register form");
        System.out.println(userForms);

    //validation form data
    if(rBindingResult.hasErrors()){
   return "register";
    }

    User user = new User();
    user.setName(userForms.getName());
    user.setEmail(userForms.getEmail());    
    user.setPassword(userForms.getPassword());
    user.setAbout(userForms.getAbout());    
    user.setPhoneNumber(userForms.getPhoneNumber());    
    user.setProfilePic("https://www.bing.com/th/id/OIP.ayTo9QqFkMrGwoKyrjpjXwHaHa?w=157&h=211&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2");
    
       User saveUser= userService.saveUser(user);
       System.out.println("saved user"+saveUser);

//message success
     message  Message=  message.builder().content("Successfully Registered !!  ").type(MessageType.green).build();
             
         session.setAttribute("message", Message);


        return "redirect:/register";
    }

    

   
}