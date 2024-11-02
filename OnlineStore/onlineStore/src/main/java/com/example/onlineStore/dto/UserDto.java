package com.example.onlineStore.dto;

import java.util.Set;

public record UserDto(String name, String email, Boolean isAdmin, Set<String> roles) {

}
