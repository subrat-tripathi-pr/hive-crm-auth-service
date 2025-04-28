package com.hivecrm.auth.mapper;

import com.hivecrm.auth.dto.RegisterRequest;
import com.hivecrm.auth.dto.User;
import com.hivecrm.auth.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity mapToUserEntity(User user);

    UserEntity mapToUserEntity(RegisterRequest request);

    User mapToUser(UserEntity userEntity);
}
