package com.SCM.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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


@Entity(name = "user")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
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

    private boolean enabled = false;

    private boolean emailVerified = false;

    private boolean phoneVerified = false;

    //Self ,google,facebook,github
    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String provideUserId;



@OneToMany(
 mappedBy = "user",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    orphanRemoval = true
) private List<Contact> contacts=new ArrayList<>();

  
}
