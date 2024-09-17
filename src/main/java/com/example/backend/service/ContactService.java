package com.example.backend.service;

import com.example.backend.controller.ContactController;
import com.example.backend.model.Contact;
import com.example.backend.repository.ContactRequestRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRequestRepository contactRequestRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Contact saveContactRequest(Contact contactRequest) {
        // Save the contact request to MongoDB
        return contactRequestRepository.save(contactRequest);
    }

    public void sendEmail(Contact contactRequest) {
        // Send an email using JavaMailSender
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(contactRequest.getEmail());
        message.setSubject("Thank you for contacting us");
        message.setText("Dear " + contactRequest.getName() + ",\n\nThank you for your message: " + contactRequest.getMessage() + "\n\nWe'll get back to you soon!");

        mailSender.send(message);
    }

	public List<Contact> getAllContacts() {
	    return contactRequestRepository.findAll(); // Retrieve all contacts from MongoDB
	}
}
