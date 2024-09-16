package com.example.backend.repository;

import com.example.backend.model.Order;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // Custom query methods can be defined here if needed
    // For example, find orders by userId
    List<Order> findByUserId(String userId);
}
