package com.gialongchuai.identity.mapper;

import com.gialongchuai.identity.dto.request.ProfileCreationRequest;
import com.gialongchuai.identity.dto.request.UserCreationRequest;
import com.gialongchuai.identity.dto.response.ProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest userCreationRequest);
}
