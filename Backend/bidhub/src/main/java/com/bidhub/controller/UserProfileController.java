package com.bidhub.controller;

import com.bidhub.dto.UserProfileDTO;
import com.bidhub.dto.UserResponseDTO;
import com.bidhub.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateProfile(@PathVariable String userId, @Valid @RequestBody UserProfileDTO profileDTO) {
        UserResponseDTO response = userProfileService.updateProfile(userId, profileDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getProfile(@PathVariable String userId) {
        UserResponseDTO response = userProfileService.getProfile(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/complete")
    public ResponseEntity<Boolean> isProfileComplete(@PathVariable String userId) {
        boolean response = userProfileService.isProfileComplete(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}