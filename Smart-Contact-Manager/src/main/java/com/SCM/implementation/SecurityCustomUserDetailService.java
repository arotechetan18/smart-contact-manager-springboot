package com.SCM.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SCM.repositories.Userepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

      @Autowired
       private Userepo userRepo;
       
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //laod the user from databse

   userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found with this email"+username));
        return null;
     
    }


}
