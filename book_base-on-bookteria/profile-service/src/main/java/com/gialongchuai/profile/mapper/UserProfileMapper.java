package com.gialongchuai.profile.mapper;

import com.gialongchuai.profile.dto.request.UserProfileCreationRequest;
import com.gialongchuai.profile.dto.request.UserProfileUpdationRequest;
import com.gialongchuai.profile.dto.response.UserProfileResponse;
import com.gialongchuai.profile.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(UserProfileCreationRequest userProfileCreationRequest);
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
    void updateUserProfile(@MappingTarget UserProfile userProfile, UserProfileUpdationRequest userProfileUpdationRequest);
}
