package com.gialongchuai.gateway.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gialongchuai.gateway.dto.response.ApiResponse;
import com.gialongchuai.gateway.service.IdentityService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {
    IdentityService identityService;
    ObjectMapper objectMapper;

    // Do trong auth khong co gi quan trong nen .* cho het khong cap xac thuc
    // o day loai bo xac thuc qua endpoint /identity/users/registration nhung xuong tang service van
    // keu xac thuc nen la o duoi service them cai loai bo endpoint nay
    @NonNull
    private String[] publicEndpoints = {
            "/identity/auth/.*", "/identity/users/registration",
            "/notification/email/send",
    };

    @Value("${app.api-path-prefix}")
    @NonFinal
    private String apiPrefix;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Authenticate api-gateway...");

        if (isPublicEndpoint(exchange.getRequest())) return chain.filter(exchange);

        // Get token from authorization header
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(authHeader)) {
            return unauthenticated(exchange.getResponse());
        }

        String token = authHeader.getFirst().replace("Bearer ", "");
        // log.info("Token: {}", token);
        // Verify token
        // Delegate identity_service

        // Xac thuc token hop le khong dung quang loi unauthenticated
        return identityService.introspect(token).flatMap(introSpectResponseApiResponse -> {
            if (introSpectResponseApiResponse.getResult().isValid()) {
                return chain.filter(exchange);
            } else {
                return unauthenticated(exchange.getResponse());
            }
        }).onErrorResume(throwable -> unauthenticated(exchange.getResponse()));
    }

    @Override
    public int getOrder() {
        return -1;
    }

    Mono<Void> unauthenticated(ServerHttpResponse serverHttpResponse) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(1401)
                .message("Unauthenticated")
                .build();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        serverHttpResponse.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return serverHttpResponse.writeWith(
                Mono.just(serverHttpResponse.bufferFactory().wrap(body.getBytes())));
    }
    // Co the su dung equal cho chac chan hon hihi
    private boolean isPublicEndpoint(ServerHttpRequest serverHttpRequest) {
        return Arrays.stream(publicEndpoints).anyMatch(s ->
                serverHttpRequest.getURI().getPath().matches(apiPrefix + s));
    }
}
