package com.SCM.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.CascadeType;

///user entity
@Entity(name = "user")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User  implements UserDetails{
    @Id
    private String userid;

    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String about;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String profilePic;

    private String phoneNumber;//

    // information

    private boolean enabled = true;

    private boolean emailVerified = false;

    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    //Self ,google,facebook,github
    private Providers provider = Providers.SELF;
    private String provideUserId;


//one user have many contacts
@OneToMany(
 mappedBy = "user",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    orphanRemoval = true
) private List<Contact> contacts=new ArrayList<>();



// Stores user roles (ROLE_USER, ROLE_ADMIN)
  @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    // Converts roles into Spring Security authorities
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
 
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;
}


// Returns email as username
@Override
public String getUsername() {
   return this.email;
}

  
}
