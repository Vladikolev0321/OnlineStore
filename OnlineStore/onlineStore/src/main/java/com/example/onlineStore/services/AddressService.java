package com.example.onlineStore.services;

import com.example.onlineStore.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.onlineStore.mappers.AddressMapper;
import com.example.onlineStore.repositories.AddressRepo;
import com.example.onlineStore.entities.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {
    private final AddressRepo addressRepo;
    private final AddressMapper addressMapper;


    @Transactional
    public Address createAddress(AddressDto addressDto) {
        Address address = addressMapper.convertDtoToEntity(addressDto);
        Address savedAddress = addressRepo.save(address);
        log.info("Address created: {}", savedAddress);
        return savedAddress;
    }

    @Transactional
    public List<Address> getAllAddresses() {
        List<Address> addresses = addressRepo.findAll();
        log.info("Addresses found: {}", addresses);
        return addresses;
    }

    @Transactional
    public Address getAddressById(long id) {
        return addressRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
    }

    public void deleteAddressById(long id) {
        Address addressForDelete = addressRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        addressRepo.delete(addressForDelete);
        log.info("Address deleted: {}", addressForDelete);
    }

    @Transactional
    public Address updateAddress(Long id, AddressDto addressDto) {
        Address addressForUpdate = addressRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        addressForUpdate.setStreet(addressDto.street());
        addressForUpdate.setCity(addressDto.city());
        addressForUpdate.setState(addressDto.state());
        addressForUpdate.setZipCode(addressDto.zipCode());

        Address savedAddress = addressRepo.save(addressForUpdate);
        log.info("Address updated: {}", savedAddress);
        return savedAddress;
    }
}
