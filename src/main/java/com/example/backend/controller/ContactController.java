package com.example.backend.controller;

import com.example.backend.model.Contact;
import com.example.backend.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")

public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<?> submitContact(@RequestBody Contact contact) {
        try {
            // Check for null contact
            if (contact == null) {
                return ResponseEntity.badRequest()
                                     .body(Map.of("error", "Contact request cannot be null"));
            }

            // Get the authenticated user email if needed
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                String email = authentication.getName();
                logger.info("Submitting contact request for user: {}", email);
            } else {
                logger.warn("Submitting contact request without authentication");
            }

            // Set the created timestamp for the contact request
            contact.setSubmittedAt(LocalDateTime.now());

            // Save the contact request via the service
            Contact savedContact = contactService.saveContactRequest(contact);
            return ResponseEntity.ok(savedContact);
        } catch (Exception e) {
            logger.error("Error submitting contact request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Error submitting contact request: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllContacts() {
        try {
            List<Contact> contacts = contactService.getAllContacts();
            return ResponseEntity.ok(contacts);
        } catch (Exception e) {
            logger.error("Error retrieving contacts: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Error retrieving contacts: " + e.getMessage()));
        }
    }
}
