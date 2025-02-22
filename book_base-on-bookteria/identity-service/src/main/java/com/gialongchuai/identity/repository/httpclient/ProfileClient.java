package com.gialongchuai.identity.repository.httpclient;

import com.gialongchuai.identity.configuration.AuthenticationRequestInterceptor;
import com.gialongchuai.identity.dto.request.ProfileCreationRequest;
import com.gialongchuai.identity.dto.response.ProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
        configuration = AuthenticationRequestInterceptor.class) // Thay vi co the su dung @Component ma nen de nhu nay cho no tien rang service nao nen su dung ...
public interface ProfileClient {
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileResponse createProfile(
            @RequestBody ProfileCreationRequest profileCreationRequest);
}
