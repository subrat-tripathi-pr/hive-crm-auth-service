package com.hivecrm.auth.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private Set<String> roles;

    public User(String username, String email,
                String password, boolean enabled, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }
}
