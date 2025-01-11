package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Getter private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    // Getters and Setters
}
