package com.hivecrm.auth.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
    private boolean enabled;
    private String status;
    private boolean emailVerified;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant lastLoginAt;
    private int loginAttempts;
    private String provider;
    private Profile profile;

    public static class Profile {
        private String fullName;
    }
}
