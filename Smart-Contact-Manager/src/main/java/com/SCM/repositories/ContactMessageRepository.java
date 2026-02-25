package com.SCM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SCM.entities.ContactMessage;

public interface ContactMessageRepository 
        extends JpaRepository<ContactMessage, Long> {
}