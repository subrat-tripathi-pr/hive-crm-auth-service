package com.hivecrm.auth.service;

import com.hivecrm.auth.domain.member.AuthRequest;
import com.hivecrm.auth.domain.member.AuthResponse;
import com.hivecrm.auth.domain.member.RegisterRequest;
import com.hivecrm.auth.domain.member.Role;
import com.hivecrm.auth.domain.member.User;
import com.hivecrm.auth.mapper.UserMapper;
import com.hivecrm.auth.model.dao.UserEntity;
import com.hivecrm.auth.repository.UserRepository;
import com.hivecrm.auth.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    public AuthResponse authenticate(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .map(userMapper::mapToUser)
                .orElseThrow();
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()) || userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Username or Email already exists");
        }

        UserEntity newUser = userMapper.mapToUserEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoles(List.of(Role.USER.name()));
        newUser.setEnabled(true);

        userRepository.save(newUser);
    }
}
