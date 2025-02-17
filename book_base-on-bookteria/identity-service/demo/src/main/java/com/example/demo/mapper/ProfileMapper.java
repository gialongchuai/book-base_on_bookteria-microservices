package com.example.demo.mapper;

import com.example.demo.dto.request.ProfileCreationRequest;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.response.ProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest userCreationRequest);
}
