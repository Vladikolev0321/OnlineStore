package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    private String paymentMethod;

    private Double amount;

    @OneToMany(mappedBy = "payment")
    private Set<Order> orders;

    // Getters and Setters
}
