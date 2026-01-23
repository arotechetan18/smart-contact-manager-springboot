package com.SCM.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SCM.entities.User;

@Repository
public interface Userepo extends JpaRepository<User,String>    {

    //database operations for user entity
    //custom query methods can be defined here if needed
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
