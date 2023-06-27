package com.example.app.services.auth.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum UserRole implements GrantedAuthority {
    ROLE_READ(Constants.ROLE_READ),
    ROLE_WRITE(Constants.ROLE_WRITE),
    ROLE_MANAGE(Constants.ROLE_MANAGE),
    ROLE_ADMIN(Constants.ROLE_ADMIN);

    private final String authority;

    public String getAuthority() {
        return this.name();
    }

    public static class Constants {
        public static final String ROLE_READ = "ROLE_READ";
        public static final String ROLE_WRITE = "ROLE_WRITE";
        public static final String ROLE_MANAGE = "ROLE_MANAGE";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
    }
}
