package com.bidhub.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfile {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private Address address;

}
