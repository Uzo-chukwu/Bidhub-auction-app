package com.bidhub.dto;
import com.bidhub.model.Role;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private Role role;
    private UserProfileDTO profile;
}
