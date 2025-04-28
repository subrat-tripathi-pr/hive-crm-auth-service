package com.hivecrm.auth.controller;

import com.hivecrm.auth.dto.AuthRequest;
import com.hivecrm.auth.dto.AuthResponse;
import com.hivecrm.auth.dto.RegisterRequest;
import com.hivecrm.auth.dto.RegisterResponse;
import com.hivecrm.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthServiceController {

    private final AuthService authService;

    public AuthServiceController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterResponse(true, "User registered successfully"));
    }

    @PostMapping("/logout")
    public void logout() {
        return ;
    }
}
