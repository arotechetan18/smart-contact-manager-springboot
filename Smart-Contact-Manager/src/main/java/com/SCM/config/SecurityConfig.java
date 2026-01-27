package com.SCM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SCM.implementation.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailService;
    //use create and login using java code in memory service 

    @Autowired
    private oAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

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
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login", "/authenticate").permitAll();
            auth.requestMatchers("/user/**").authenticated();
            auth.anyRequest().permitAll();
        })
        .formLogin(form -> {
            form.loginPage("/login");
            form.loginProcessingUrl("/authenticate");
            form.defaultSuccessUrl("/user/dashboard", true);
            // form.failureUrl("/login?error=true");
            form.usernameParameter("email");
            form.passwordParameter("password");
        });


  http.logout(logoutform -> {
      logoutform.logoutUrl("/do-logout");
      logoutform.logoutSuccessUrl("/login?logout=true");
  
  });
http.oauth2Login(oauth2 -> {
    oauth2.loginPage("/login");
    oauth2.successHandler(oAuthAuthenticationSuccessHandler);
});


    return http.build();
}

@Bean
public AuthenticationManager authenticationManager(
        AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
}


@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}




