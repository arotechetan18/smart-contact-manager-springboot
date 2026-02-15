package com.SCM.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.SCM.entities.Contact;
import com.SCM.entities.User;
import com.SCM.helpers.ResourceNotFoundException;
import com.SCM.repositories.Contactrepo;
import com.SCM.services.ContactService;

@Service
public class ContactServiceimpl implements ContactService {

     @Autowired
    private Contactrepo contactrepo;

  public Contact save(Contact contact) {
    String contactId = UUID.randomUUID().toString();
    contact.setId(contactId);

    return contactrepo.save(contact); 
}


    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
     return contactrepo.findAll();
    }

    @Override
    public Contact getbyid(String id) {
       
        return contactrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("contact not found with id"+id));
    }

    @Override
    public void delete(String id) {
     var contact=  contactrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("contact not found with id"+id));

     contactrepo.delete(contact);
    }

 
@Override
public List<Contact> getByUserId(String userId) {
   return contactrepo.findContactsByUserId(userId); 
}


@Override
public Page<Contact> getByUser(User user,int page ,int size,String sortBy,String direction) {

    Sort sort=direction.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();

var pageable=PageRequest.of(page, size,sort);

return  contactrepo.findByUser(user,pageable);
   
}


@Override
public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order,User user) {
   Sort sort=order.equals("desc")? Sort.by(sortBy).descending() :Sort.by(sortBy).ascending();

var pageable=PageRequest.of(page,size,sort);

    return contactrepo.findByUserAndNameContaining(user,nameKeyword,pageable);
}


@Override
public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,User user) {
    Sort sort=order.equals("desc")? Sort.by(sortBy).descending() :Sort.by(sortBy).ascending();

var pageable=PageRequest.of(page,size,sort);

   return contactrepo.findByUserAndEmailContaining(user,emailKeyword, pageable);

}


@Override
public Page<Contact> searchByphoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,User user) {
    Sort sort=order.equals("desc")? Sort.by(sortBy).descending() :Sort.by(sortBy).ascending();

var pageable=PageRequest.of(page,size,sort);

   return contactrepo.findByUserAndPhoneNumberContaining(user,phoneNumberKeyword, pageable);

}


 

}
