package com.hivecrm.auth.service;

import com.hivecrm.auth.dto.UserProfile;
import com.hivecrm.auth.mapper.UserProfileMapper;
import com.hivecrm.auth.model.Profile;
import com.hivecrm.auth.model.UserEntity;
import com.hivecrm.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileService(UserRepository userRepository, UserProfileMapper userProfileMapper) {
        this.userRepository = userRepository;
        this.userProfileMapper = userProfileMapper;
    }

    public UserProfile updateProfile(String userId, UserProfile userProfile) {
        UserEntity userEntity = userRepository.findByUsername(userId).orElseThrow();

        Profile profile = userProfileMapper.map(userProfile);
        userEntity.setProfile(profile);

        userRepository.save(userEntity);
        return userProfile;
    }

    public UserProfile getProfile(String userId) {
        return userRepository.findByUsername(userId)
                .map(UserEntity::getProfile)
                .map(userProfileMapper::map)
                .orElse(new UserProfile());
    }
}
