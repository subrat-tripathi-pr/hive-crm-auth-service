package com.hivecrm.auth.controller;

import com.hivecrm.auth.dto.UserProfile;
import com.hivecrm.auth.service.UserProfileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserProfileController {
    public static final String UPLOAD_DIR = "uploads/";
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> update(@PathVariable String userId, @RequestBody UserProfile userProfile) {
        return ResponseEntity.ok()
                .body(userProfileService.updateProfile(userId, userProfile));
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> get(@PathVariable String userId) {
        return ResponseEntity.ok()
                .body(userProfileService.getProfile(userId));
    }

    @PostMapping("/{userId}/profile/pic/upload")
    public ResponseEntity<?> uploadProfilePicture(@PathVariable String userId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }
        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body("Only image files are allowed!");
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            Path path = Paths.get(UPLOAD_DIR + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            userProfileService.updateProfilePic(userId, filename);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("/{userId}/profile/pic")
    public ResponseEntity<Resource> getProfilePicture(@PathVariable String userId) {
        try {
            UserProfile profile = userProfileService.getProfile(userId);
            String fileName = profile.getImageUrl();
            Path path = Paths.get(UPLOAD_DIR).resolve(fileName);
            File file = new File(UPLOAD_DIR + fileName);

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new FileSystemResource(path);
            String contentType = Files.probeContentType(path);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(Objects.nonNull(contentType)
                            ? contentType : "application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
