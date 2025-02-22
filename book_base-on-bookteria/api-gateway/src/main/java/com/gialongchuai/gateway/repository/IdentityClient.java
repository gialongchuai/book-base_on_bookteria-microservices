package com.gialongchuai.gateway.repository;


import com.gialongchuai.gateway.dto.request.IntroSpectRequest;
import com.gialongchuai.gateway.dto.response.ApiResponse;
import com.gialongchuai.gateway.dto.response.IntroSpectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntroSpectResponse>> introspect(@RequestBody IntroSpectRequest introSpectRequest);
}
