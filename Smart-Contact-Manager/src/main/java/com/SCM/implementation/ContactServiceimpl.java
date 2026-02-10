package com.SCM.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SCM.entities.Contact;
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
    public List<Contact> serch(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serch'");
    }
@Override
public List<Contact> getByUserId(String userId) {
   return contactrepo.findContactsByUserId(userId); // ✅
}

 

}
