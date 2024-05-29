package com.livethegame.ManageUser.common;

import com.livethegame.ManageUser.dto.UserResponse;
import com.livethegame.ManageUser.entities.Users;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserResponseMapper {
    UserResponse UserToUserResponse(Users source);
}
