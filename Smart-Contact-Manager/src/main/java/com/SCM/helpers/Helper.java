package com.SCM.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class Helper {

    public static String getEmailofLoggedinUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken token) {

            String provider = token.getAuthorizedClientRegistrationId();
            var principal = token.getPrincipal();

            if (provider.equalsIgnoreCase("google")) {
                System.out.println("log in with google");
                return principal.getAttribute("email");
            }

            if (provider.equalsIgnoreCase("github")) {
                System.out.println("login with gtihub");

                Object email = principal.getAttribute("email");
                return email != null
                        ? email.toString()
                        : principal.getAttribute("login").toString() + "@gmail.com";
            }
        }

        System.out.println("log in with local database");
        return authentication.getName();
    }


}
