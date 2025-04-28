package com.hivecrm.auth.mapper;

import com.hivecrm.auth.dto.UserProfile;
import com.hivecrm.auth.model.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    Profile map(UserProfile userProfile);
    UserProfile map(Profile userProfile);
}
