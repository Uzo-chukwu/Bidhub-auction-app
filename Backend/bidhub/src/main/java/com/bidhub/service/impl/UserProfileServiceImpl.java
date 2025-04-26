package com.bidhub.service.impl;

import com.bidhub.dto.AddressDTO;
import com.bidhub.dto.UserProfileDTO;
import com.bidhub.dto.UserResponseDTO;
import com.bidhub.model.Address;
import com.bidhub.model.User;
import com.bidhub.model.UserProfile;
import com.bidhub.repository.UserRepository;
import com.bidhub.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserRepository userRepository;

    @Autowired
    public UserProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO updateProfile(String userId, UserProfileDTO profileDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserProfile profile = new UserProfile();
        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setDateOfBirth(profileDTO.getDateOfBirth());
        profile.setGender(profileDTO.getGender());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setAddress(profileDTO.getAddress() != null ? mapToAddress(profileDTO.getAddress()) : null);

        user.setProfile(profile);
        User savedUser = userRepository.save(user);
        return mapToUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return mapToUserResponseDTO(user);
    }

    @Override
    public boolean isProfileComplete(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        UserProfile profile = user.getProfile();
        return profile != null &&
                profile.getFirstName() != null &&
                profile.getLastName() != null &&
                profile.getDateOfBirth() != null &&
                profile.getGender() != null &&
                profile.getPhoneNumber() != null &&
                profile.getAddress() != null &&
                profile.getAddress().getStreet() != null &&
                profile.getAddress().getCity() != null &&
                profile.getAddress().getState() != null &&
                profile.getAddress().getZipCode() != null;
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

    private Address mapToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        return address;
    }
}