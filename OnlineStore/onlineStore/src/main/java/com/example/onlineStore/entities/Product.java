package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter
    private Long id;


   @Setter @Getter private String name;

   @Setter @Getter private String description;

   @Setter @Getter private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_tag_id")
    )
    private Set<ProductTag> productTags;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;

    // Getters and Setters
}