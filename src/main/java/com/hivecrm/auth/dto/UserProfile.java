package com.hivecrm.auth.dto;

import lombok.Data;

@Data
public class UserProfile {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String city;
    private String address;
    private String company;
    private String phone;
    private String dateOfBirth;
    private String socialMedia;
}
