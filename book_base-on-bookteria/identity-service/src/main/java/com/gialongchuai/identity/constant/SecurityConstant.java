package com.gialongchuai.identity.constant;

public class SecurityConstant {
    public static final String[] PUBLIC_ENDPOINTS = {
        "/users/registration", "/auth/introspect", "/auth/token", "/auth/logout", "/auth/refresh"
    };

    public static final String JWT_AUTHORITY_PREFIX = "";
    public static final int BCRYPT_STRENGTH = 10;
}
