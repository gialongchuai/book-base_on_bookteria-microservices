package com.gialongchuai.post.repository.httpclient;

import com.gialongchuai.post.dto.response.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

    @FeignClient(name = "profile-service", url = "${app.services.profile}")
public interface ProfileClient {
    @GetMapping("/users/{userId}")
    UserProfileResponse getProfileByUserId(@PathVariable String userId);
}
