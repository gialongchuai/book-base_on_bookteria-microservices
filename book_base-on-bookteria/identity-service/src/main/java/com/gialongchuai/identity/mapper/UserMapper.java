package com.gialongchuai.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.gialongchuai.identity.dto.request.UserCreationRequest;
import com.gialongchuai.identity.dto.request.UserUpdationRequest;
import com.gialongchuai.identity.dto.response.UserResponse;
import com.gialongchuai.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
    // List<UserResponse> toUsersResponse(List<User> user);
    User toUser(UserCreationRequest userCreationRequest);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdationRequest userUpdationRequest);
}
