package com.example.onlineStore.repositories;
import com.example.onlineStore.entities.Order;
import com.example.onlineStore.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
