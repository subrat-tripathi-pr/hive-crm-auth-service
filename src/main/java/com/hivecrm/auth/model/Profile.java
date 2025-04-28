package com.hivecrm.auth.model;

import lombok.Data;

@Data
public class Profile {
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
    private String imageFileName;
}
