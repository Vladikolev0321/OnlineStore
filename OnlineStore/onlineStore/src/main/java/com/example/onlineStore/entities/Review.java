package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String comment;

    @Getter @Setter
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Getters and Setters
}