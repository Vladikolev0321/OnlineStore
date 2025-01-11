package com.example.onlineStore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;

    // If you have a User entity:
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // If you have a Payment entity:
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    // Constructors
    public Order() {
    }

    public Order(Double totalAmount, User user, Payment payment) {
        this.totalAmount = totalAmount;
        this.user = user;
        this.payment = payment;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
