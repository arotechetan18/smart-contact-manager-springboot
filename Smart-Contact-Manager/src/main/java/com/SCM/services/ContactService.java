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
      Page<Contact>searchByName(String nameKeyword,int size,int page,String sortBy,String order,User user);

       Page<Contact>searchByEmail(String emailKeyword,int size,int page,String sortBy,String order,User user);

        Page<Contact>searchByphoneNumber(String phoneNumberKeyword,int size,int page,String sortBy,String order,User user);


      //get conatct by user id
      List<Contact>getByUserId(String userId);


      Page<Contact>getByUser(User user,int page ,int size,String sortField,String sortDirection);

    Page<Contact> getFavouriteByUser(User user, int page, int size, String sortBy, String direction);

    List<Contact> getAllByUser(User user);
}
