package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.Product;
import com.example.onlineStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
