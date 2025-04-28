package com.hivecrm.auth.controller;

import com.hivecrm.auth.dto.UserProfile;
import com.hivecrm.auth.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> update(@PathVariable String userId, @RequestBody UserProfile userProfile){
        return ResponseEntity.ok()
                .body(userProfileService.updateProfile(userId, userProfile));
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> get(@PathVariable String userId){
        return ResponseEntity.ok()
                .body(userProfileService.getProfile(userId));
    }
}
