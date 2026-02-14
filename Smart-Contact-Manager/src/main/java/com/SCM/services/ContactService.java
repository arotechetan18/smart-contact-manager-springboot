package com.SCM.services;

import java.util.List;

import org.springframework.data.domain.Page;
import com.SCM.entities.Contact;
import com.SCM.entities.User;

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


      Page<Contact>getByUser(User user,int page ,int size,String sortField,String sortDirection);

    
}
