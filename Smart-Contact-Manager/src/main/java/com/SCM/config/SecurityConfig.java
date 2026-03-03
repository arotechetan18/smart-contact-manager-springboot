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

// Main Spring Security configuration class for authentication and authorization
@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailService;
    // Custom UserDetailsService implementation to load user from database

    @Autowired
    private oAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;
    // Handles custom logic after successful OAuth2 login

    // configure authentication provider daoauthenticationprovider by spring
    // security

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Create DAO authentication provider
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detailservice object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        // Set password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // Configure HTTP security rules and login/logout settings
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Allow login and authentication URLs without login
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login", "/authenticate").permitAll();
                    // for user-related URLs
                    auth.requestMatchers("/user/**").authenticated();
                    auth.anyRequest().permitAll();
                })

                // Configure custom form login
                .formLogin(form -> {
                    form.loginPage("/login");
                    form.loginProcessingUrl("/authenticate");

                    // Redirect after successful login
                    form.defaultSuccessUrl("/home", true);

                    // form.failureUrl("/login?error=true");
                    form.usernameParameter("email");
                    form.passwordParameter("password");
                });
        // logout

        http.logout(logoutform -> {
            logoutform.logoutUrl("/do-logout");

            // Redirect after logout
            logoutform.logoutSuccessUrl("/login?logout=true");

        });

        // Configure OAuth2 login (Google, github.)
        http.oauth2Login(oauth2 -> {
            oauth2.loginPage("/login");
            oauth2.successHandler(oAuthAuthenticationSuccessHandler);
        });

        return http.build();
    }

    //  AuthenticationManager bean
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
