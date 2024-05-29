package com.livethegame.ManageUser.common;

import com.livethegame.ManageUser.dto.UserRequest;
import com.livethegame.ManageUser.entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    Users UserRequestToUser(UserRequest source);
}
