package com.SCM.services;

import java.util.List;

import com.SCM.entities.Contact;
import com.SCM.entities.User;
import com.SCM.forms.Contactform;

public interface ContactService {


    //SAVE
  Contact save(Contact contact);

//update
      Contact update(Contact contact);

      //get conatact
      List<Contact>getAll();

      //getconatct  by id 
      Contact getbyid(String id);

      //delete contact by id
      void delete(String id);

      //serch contact
      List<Contact>serch(String name,String email,String phoneNumber);


      //get conatct by user id
      List<Contact>getByUserId(String userId);

      List<Contact>getByUser(User user);

    
}
