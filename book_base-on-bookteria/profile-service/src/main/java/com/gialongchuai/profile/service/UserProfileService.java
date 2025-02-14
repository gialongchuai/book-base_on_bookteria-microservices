package com.gialongchuai.profile.service;

import com.gialongchuai.profile.dto.request.UserProfileCreationRequest;
import com.gialongchuai.profile.dto.request.UserProfileUpdationRequest;
import com.gialongchuai.profile.dto.response.UserProfileResponse;
import com.gialongchuai.profile.entity.UserProfile;
import com.gialongchuai.profile.mapper.UserProfileMapper;
import com.gialongchuai.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    public UserProfileResponse create(UserProfileCreationRequest userProfileCreationRequest) {
        UserProfile userProfile = userProfileMapper.toUserProfile(userProfileCreationRequest);

        return (userProfileMapper.toUserProfileResponse(userProfileRepository.save(userProfile)));
    }

    public UserProfileResponse getUserProfile(String userProfileId) {
        UserProfile userProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new RuntimeException("User profile not exist!"));

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public List<UserProfileResponse> getUserProfiles() {
        var users = userProfileRepository.findAll();
        return users.stream().map(userProfileMapper::toUserProfileResponse).toList();
    }

    public UserProfileResponse updateUserProfile(String profileId, UserProfileUpdationRequest userUpdationRequest) {
        UserProfile userProfile = userProfileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("User profile not exist!"));

        userProfileMapper.updateUserProfile(userProfile, userUpdationRequest);

        return userProfileMapper.toUserProfileResponse(userProfileRepository.save(userProfile));
    }

    public void deleteUserProfile(String profileId) {
        userProfileRepository.deleteById(profileId);
    }
}
