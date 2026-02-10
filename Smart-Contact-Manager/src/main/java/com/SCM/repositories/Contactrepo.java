package com.SCM.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SCM.entities.Contact;
import com.SCM.entities.User;
@Repository
public interface Contactrepo extends JpaRepository<Contact, String> {

    // find contacts by User object
    List<Contact> findByUser(User user);

    // find contacts by userId (JPQL)
@Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
List<Contact> findContactsByUserId(@Param("userId") String userId);

}
