package com.hivecrm.auth.mapper;

import com.hivecrm.auth.dto.UserProfile;
import com.hivecrm.auth.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "imageFileName", source = "imageUrl")
    Profile map(UserProfile userProfile);
    @Mapping(target = "imageUrl", source = "imageFileName")
    UserProfile map(Profile userProfile);
}
