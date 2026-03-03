package com.SCM.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SCM.entities.User;
import com.SCM.repositories.Userepo;
// Used to load user details during authentication
@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    @Autowired
    private Userepo userRepository;

       // Load user by email (used as username)
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

       

        if (email == null || email.trim().isEmpty()) {
            throw new UsernameNotFoundException("Email is empty");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("User is disabled");
        }


        // Build Spring Security User object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .accountLocked(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
