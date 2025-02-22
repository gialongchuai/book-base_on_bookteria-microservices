package com.gialongchuai.gateway.service;

import com.gialongchuai.gateway.dto.request.IntroSpectRequest;
import com.gialongchuai.gateway.dto.response.ApiResponse;
import com.gialongchuai.gateway.dto.response.IntroSpectResponse;
import com.gialongchuai.gateway.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ApiResponse<IntroSpectResponse>> introspect(String token) {
        return identityClient.introspect(IntroSpectRequest.builder()
                        .token(token)
                .build());
    }
}
