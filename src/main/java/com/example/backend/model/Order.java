package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

/**
 * Represents an order placed by a user.
 */
@Document(collection = "orders")
public class Order {

    @Id
    private String id;  // Unique identifier for the order
    private String userId;  // User ID or email who placed the order
    private List<OrderItem> orderItems;  // List of items in the order
    private double totalAmount;  // Total amount for the order
    private String paymentMethod;  // Payment method used for the order
    private Date orderDate;  // Date when the order was placed
    private String status;  // Current status of the order

    // Inner class for OrderItem
    public static class OrderItem {
        private String productId;  // Product ID for the item
        private String service;  // Service associated with the order item
        private double price;  // Price of the item
        private int quantity;  // Quantity of the item ordered

        // Default constructor
        public OrderItem() {
        }

        // Parameterized constructor
        public OrderItem(String productId, String service, double price, int quantity) {
            this.productId = productId;
            this.service = service;
            this.price = price;
            this.quantity = quantity;
        }

        // Getters and Setters
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // Default constructor
    public Order() {
        this.orderDate = new Date(); // Set the order date to current time by default
        this.status = "Pending"; // Set default status
    }

    // Parameterized constructor
    public Order(String id, String userId, List<OrderItem> orderItems, double totalAmount, String paymentMethod) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.orderDate = new Date(); // Set the order date to current time
        this.status = "Pending"; // Set default status
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
