package com.bidhub.service;

import com.bidhub.dto.UserProfileDTO;
import com.bidhub.dto.UserResponseDTO;

public interface UserProfileService {
    UserResponseDTO updateProfile(String userId, UserProfileDTO profileDTO);
    UserResponseDTO getProfile(String userId);
    boolean isProfileComplete(String userId);
}