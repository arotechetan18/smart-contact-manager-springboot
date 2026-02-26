package com.SCM.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SCM.entities.Contact;
import com.SCM.entities.User;
@Repository
public interface Contactrepo extends JpaRepository<Contact, String> {

    // find contacts by User object
    Page<Contact> findByUser(User user,Pageable pageable);

    // find contacts by userId (JPQL) custom query
@Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
List<Contact> findContactsByUserId(@Param("userId") String userId);

Page<Contact> findByUserAndNameContaining(User user,String nameKeyword,Pageable pageable);
Page<Contact> findByUserAndEmailContaining(User user,String emailKeyword,Pageable pageable);
Page<Contact> findByUserAndPhoneNumberContaining(User user,String phoneNumberKeyword,Pageable pageable);
Page<Contact> findByUserAndFavouriteTrue(User user, Pageable pageable);

List<Contact> findByUser(User user);

long countByUser(User user);

long countByUserAndFavouriteTrue(User user);

List<Contact> findTop5ByUserOrderByIdDesc(User user);
}
