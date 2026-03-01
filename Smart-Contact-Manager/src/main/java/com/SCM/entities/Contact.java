package com.SCM.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts")
public class Contact {
  @Id

  private String id;

  private String name;
  private String email;
  private String phoneNumber;
  private String address;
  private String picture;
  @Column(length = 500)
  private String description;
  private boolean favourite = false;
private String websiteLink;
private String linkedInLink;
private String cloudinaryImagePublicId;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "user_id")
@JsonIgnore
private User user;




  @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Socialink> links = new ArrayList<>();
}
