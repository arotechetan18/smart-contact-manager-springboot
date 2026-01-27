package com.SCM.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

  Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

  @Autowired
  private Userepo userepo;

  @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("oAuthAuthenticationSuccessHandler");


        //konhi kontya acount ni login krto tyasathi he fn
   OAuth2AuthenticationToken oauth2AuthenticationToken =
        (OAuth2AuthenticationToken) authentication;

DefaultOAuth2User oAuth2User =
        (DefaultOAuth2User) oauth2AuthenticationToken.getPrincipal();

String authclientregistrationid =
        oauth2AuthenticationToken.getAuthorizedClientRegistrationId();


logger.info(authclientregistrationid);

User user =new User();
user.setUserid(UUID.randomUUID().toString());
user.setRoleList(List.of(AppConstant.ROLE_USER));
user.setEmailVerified(false);
 user.setEnabled(true);
 user.setPassword("pass123");


if(authclientregistrationid.equalsIgnoreCase("google")){

//goggle
//google attributes
user.setEmail(oAuth2User.getAttribute("email").toString());
user.setProfilePic(oAuth2User.getAttribute("picture").toString());
user.setName(oAuth2User.getAttribute("name").toString());
user.setProvideUserId(oAuth2User.getName());
user.setProvider(Providers.GOOGLE);
user.setAbout("This account is created using google");



}else if(authclientregistrationid.equalsIgnoreCase("github")){
  //github
  //gtihub attributes
     String email = oAuth2User.getAttribute("email") != null ? oAuth2User.getAttribute("email").toString()
                    : oAuth2User.getAttribute("login").toString() + "@gmail.com";
            String picture = oAuth2User.getAttribute("avatar_url").toString();
            String name = oAuth2User.getAttribute("login").toString();
  String providerUserId = oAuth2User.getName();
 user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProvideUserId(providerUserId);
            user.setProvider(Providers.GITHUB);

            user.setAbout("This account is created using github");

}else{
  logger.info("Unknown provider");
}





        // logger.info(user.getName());

        // user.getAttributes().forEach((key,value)->{
        //     logger.info("{}=>{}",key,value);
        // });

        // logger.info(user.getAuthorities().toString());
        /* 

      //   String email = user.getAttribute("email").toString();
      //   String name = user.getAttribute("name").toString();
      //   String picture= user.getAttribute("picture").toString();
      //   //crate suer and save to database 
      //   User user=new User();
      //   user.setEmail(email);
      //   user.setName(name);
      //   user.setProfilePic(picture);
      //   user.setPassword(picture);
      //   user.setUserid(UUID.randomUUID().toString());
      //   user.setProvider(Providers.GOOGLE);
      //   user.setEnabled(true);
      //   user.setEmailVerified(true);
      //   user.setRoleList(List.of(AppConstant.ROLE_USER));

      //   user.setAbout("this acount is creted usin goggle");

      // User user2=  userepo.findByEmail(email).orElse(null);

      // if(user2==null){
      //   userepo.save(user);
      //   logger.info("new user created using google account"+email);
      // }*/

 User user1 =userepo.findByEmail(user.getEmail()).orElse(null);
       if(user1==null){
        userepo.save(user);
      
        // Redirect to user dashboard or any other page
       new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }


}
}