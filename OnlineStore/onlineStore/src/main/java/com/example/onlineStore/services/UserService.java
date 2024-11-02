package com.example.onlineStore.services;

import com.example.onlineStore.dto.UserDto;
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
}
