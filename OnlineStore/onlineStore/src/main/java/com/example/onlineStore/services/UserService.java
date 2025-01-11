package com.example.onlineStore.services;

import com.example.onlineStore.dto.ProductDto;
import com.example.onlineStore.dto.UserDto;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.entities.Role;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.mappers.UserMapper;
import com.example.onlineStore.repositories.RoleRepo;
import com.example.onlineStore.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final RoleRepo roleRepo;

    @Transactional
    public User createUser(UserDto userDto) {
        User user = userMapper.convertDtoToEntity(userDto);

        Set<Role> roles = new HashSet<>(roleRepo.findByNameIn(userDto.roles()));
        user.setRoles(roles);
        User savedUser = userRepo.save(user);

        log.info("User created: {}", savedUser);
        return savedUser;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        List<User> allUsers = userRepo.findAll();
        return allUsers;
    }

    public User getById(long id) {
        return userRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional
    public  void  deleteUserById(long id) {
        User userForDelete = userRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userRepo.delete(userForDelete);

        log.info("User deleted: {}", userForDelete);
    }

    @Transactional
    public  User updateUser(Long id,UserDto userDto) {
        User userForUpdate = userRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userForUpdate.setName(userDto.name());
        userForUpdate.setEmail(userDto.email());

        User updatedUser = userRepo.save(userForUpdate);

        log.info("User updated: {}", updatedUser);
        return updatedUser;
    }
}
