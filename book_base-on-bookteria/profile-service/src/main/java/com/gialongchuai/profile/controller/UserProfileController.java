package com.gialongchuai.profile.controller;

import com.gialongchuai.profile.dto.request.UserProfileCreationRequest;
import com.gialongchuai.profile.dto.request.UserProfileUpdationRequest;
import com.gialongchuai.profile.dto.response.UserProfileResponse;
import com.gialongchuai.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @PostMapping("")
    UserProfileResponse create(@RequestBody  UserProfileCreationRequest userProfileCreationRequest) {
        return userProfileService.create(userProfileCreationRequest);
    }

    @GetMapping("/{profileId}")
    UserProfileResponse getUserProfile(@PathVariable String profileId) {
        return userProfileService.getUserProfile(profileId);
    }

    @GetMapping("")
    List<UserProfileResponse> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @PutMapping("/{profileId}")
    UserProfileResponse updateUserProfile(@PathVariable String profileId, @RequestBody UserProfileUpdationRequest userUpdationRequest) {
        return userProfileService.updateUserProfile(profileId, userUpdationRequest);
    }

    @DeleteMapping("/{profileId}")
    String deleteUser(@PathVariable String profileId) {
        userProfileService.deleteUserProfile(profileId);
        return "Delete user profile successfully!";
    }
}
