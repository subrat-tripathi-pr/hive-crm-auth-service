package com.hivecrm.auth.service;

import com.hivecrm.auth.dto.User;
import com.hivecrm.auth.mapper.UserMapper;
import com.hivecrm.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User getUser(String userId) {
        return userRepository.findByUsername(userId)
                .map(userMapper::mapToUser)
                .orElseThrow();

    }
}
