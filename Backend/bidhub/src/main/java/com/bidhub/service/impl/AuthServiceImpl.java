package com.bidhub.service.impl;

import com.bidhub.dto.AddressDTO;
import com.bidhub.dto.UserDTO;
import com.bidhub.dto.UserProfileDTO;
import com.bidhub.dto.UserResponseDTO;
import com.bidhub.model.Address;
import com.bidhub.model.Role;
import com.bidhub.model.User;
import com.bidhub.model.UserProfile;
import com.bidhub.repository.UserRepository;
import com.bidhub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO register(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        return mapToUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return mapToUserResponseDTO(user);
    }

    private UserResponseDTO mapToUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setProfile(user.getProfile() != null ? mapToUserProfileDTO(user.getProfile()) : null);
        return dto;
    }

    private UserProfileDTO mapToUserProfileDTO(UserProfile profile) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setGender(profile.getGender());
        dto.setPhoneNumber(profile.getPhoneNumber());
        dto.setAddress(profile.getAddress() != null ? mapToAddressDTO(profile.getAddress()) : null);
        return dto;
    }

    private AddressDTO mapToAddressDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipCode(address.getZipCode());
        return dto;
    }
}