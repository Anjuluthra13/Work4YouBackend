package com.example.backend.controller;

import com.example.backend.model.HireRequest;
import com.example.backend.service.HireRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HireRequestController {

    @Autowired
    private HireRequestService hireRequestService;

    // POST request to save a new hire request
    @PostMapping("/hire")
    public ResponseEntity<?> submitHireRequest(@RequestBody HireRequest hireRequest) {
        HireRequest savedHireRequest = hireRequestService.saveHireRequest(hireRequest);
        return ResponseEntity.ok(savedHireRequest);
    }

    // GET request to retrieve all hire requests (for admin or user purposes)
    @GetMapping("/hire")
    public ResponseEntity<List<HireRequest>> getAllHireRequests() {
        List<HireRequest> hireRequests = hireRequestService.getAllHireRequests();
        return ResponseEntity.ok(hireRequests);
    }
}
