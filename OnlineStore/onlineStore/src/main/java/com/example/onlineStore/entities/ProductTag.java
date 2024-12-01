package com.example.onlineStore.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_tags")
public class ProductTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "productTags")  // Corrected 'mappedBy' to reference 'productTags'
    private Set<Product> products;

    // Getters and Setters
}
