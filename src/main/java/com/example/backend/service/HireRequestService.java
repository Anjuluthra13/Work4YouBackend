package com.example.backend.service;

import com.example.backend.model.HireRequest;
import com.example.backend.repository.HireRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HireRequestService {

    @Autowired
    private HireRequestRepository hireRequestRepository;

    // Save each hire request
    public HireRequest saveHireRequest(HireRequest hireRequest) {
        return hireRequestRepository.save(hireRequest);
    }

    // Retrieve all hire requests
    public List<HireRequest> getAllHireRequests() {
        return hireRequestRepository.findAll();
    }
}
