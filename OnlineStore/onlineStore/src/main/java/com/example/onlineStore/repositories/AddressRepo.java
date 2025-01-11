package com.example.onlineStore.repositories;
import com.example.onlineStore.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}