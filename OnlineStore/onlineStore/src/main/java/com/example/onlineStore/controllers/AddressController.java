package com.example.onlineStore.controllers;

import com.example.onlineStore.dto.AddressDto;
import com.example.onlineStore.entities.Address;
import com.example.onlineStore.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto) {
        Address createdAddress = addressService.createAddress(addressDto);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> allAddresses = addressService.getAllAddresses();
        return new ResponseEntity<>(allAddresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable long id) {
        Address address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable long id) {
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable long id, @RequestBody AddressDto addressDto) {
        Address address = addressService.updateAddress(id, addressDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
