package com.bidhub.service;

import com.bidhub.dto.UserDTO;
import com.bidhub.dto.UserResponseDTO;

public interface AuthService {
    UserResponseDTO register(UserDTO userDTO);
    UserResponseDTO findByEmail(String email);
}