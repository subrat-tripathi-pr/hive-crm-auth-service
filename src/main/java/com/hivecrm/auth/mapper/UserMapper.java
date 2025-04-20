package com.hivecrm.auth.mapper;

import com.hivecrm.auth.domain.member.RegisterRequest;
import com.hivecrm.auth.domain.member.User;
import com.hivecrm.auth.model.dao.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity mapToUserEntity(User user);

    UserEntity mapToUserEntity(RegisterRequest request);

    User mapToUser(UserEntity userEntity);
}
