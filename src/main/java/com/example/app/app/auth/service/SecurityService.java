package com.example.app.app.auth.service;

import com.example.app.common.provider.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final AuthenticationManager authenticationManager;

    public void authenticateToken(UserDetails user, String jwt) {
        var token = new JwtAuthenticationToken(jwt, user.getUsername(), user.getAuthorities());
        authenticationManager.authenticate(token);
    }
}