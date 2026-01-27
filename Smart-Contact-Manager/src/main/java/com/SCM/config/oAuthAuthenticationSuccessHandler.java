package com.SCM.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.SCM.entities.Providers;
import com.SCM.entities.User;
import com.SCM.helpers.AppConstant;
import com.SCM.repositories.Userepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class oAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


Logger logger=LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

@Autowired
private Userepo userepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("oAuthAuthenticationSuccessHandler");

        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        // logger.info(user.getName());

        // user.getAttributes().forEach((key,value)->{
        //     logger.info("{}=>{}",key,value);
        // });

        // logger.info(user.getAuthorities().toString());
        

        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture= user.getAttribute("picture").toString();
        //crate suer and save to database 
        User user1=new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setPassword(picture);
        user1.setUserid(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setRoleList(List.of(AppConstant.ROLE_USER));

        user1.setAbout("this acount is creted usin goggle");

      User user2=  userepo.findByEmail(email).orElse(null);

      if(user2==null){
        userepo.save(user1);
        logger.info("new user created using google account"+email);
      }

        // Redirect to user dashboard or any other page
       new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }


}
