package com.example.onlineStore.repositories;
import com.example.onlineStore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CategoryRepo extends  JpaRepository<Category, Long> {
}