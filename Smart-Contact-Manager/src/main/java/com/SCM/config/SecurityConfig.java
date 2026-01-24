package com.SCM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SCM.implementation.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailService;
    //use create and login using java code in memory service 

//     @Bean
//     public UserDetailsService userDetailsService(){

//    UserDetails user=   User.withUsername("chetan").password("{noop}chetan123") // {noop} indicates that no encoding is use
//    .roles("ADMIN","USER")
//    .build();
  
//         var inMemoryUserDetailsManager= new InMemoryUserDetailsManager(user);

//         return inMemoryUserDetailsManager;
//     }

//configure authentication provider daoauthenticationprovider by spring security

@Bean
public AuthenticationProvider authenticationProvider() {

     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
     //user detailservice object
     daoAuthenticationProvider.setUserDetailsService(userDetailService);    
     daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
}

@Bean
public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity) throws Exception {

    //coanfigure security filter chain
    httpSecurity.authorizeHttpRequests(authorise->{
        // authorise.requestMatchers("/home","/register","/services").permitAll();

        authorise.requestMatchers("/user/**").authenticated();
        authorise.anyRequest().permitAll();
        try {
            //form default login
            httpSecurity.formLogin(Customizer.withDefaults());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    });

    return httpSecurity.build();
    
}


@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}




