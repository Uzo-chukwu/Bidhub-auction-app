package com.bidhub.controller;

import com.bidhub.dto.RegisterUserRequest;
import com.bidhub.model.User;
import com.bidhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequest request) {
        User registered = userService.register(request);
        return ResponseEntity.ok(registered);
    }

}
