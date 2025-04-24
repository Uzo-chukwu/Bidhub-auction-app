package com.bidhub.dto;


import com.bidhub.model.Gender;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class UserProfileDTO {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private Gender gender;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private AddressDTO address;
}
