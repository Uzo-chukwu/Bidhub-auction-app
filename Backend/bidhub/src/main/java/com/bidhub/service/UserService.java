package com.bidhub.service;

import com.bidhub.dto.RegisterUserRequest;
import com.bidhub.model.User;

public interface UserService {
    User register(RegisterUserRequest request);
    User findByEmail(String email);
}
