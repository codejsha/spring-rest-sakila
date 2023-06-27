package com.example.app.app.auth.controller;

import com.example.app.app.auth.domain.dto.AuthorityDto;
import com.example.app.app.auth.service.JpaUserDetailsService;
import com.example.app.app.auth.service.SecurityService;
import com.example.app.common.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
@RequiredArgsConstructor
public class LoginController {
    private final JwtTokenProvider jwtTokenProvider;
    private final JpaUserDetailsService userDetailsService;
    private final SecurityService securityService;

    @PostMapping(path = "")
    public ResponseEntity<Void> userLogin(@RequestBody AuthorityDto.Login login) {
        var user = userDetailsService.findUser(login.getEmail(), login.getPassword());
        var jwt = jwtTokenProvider.createToken(user);
        securityService.authenticateToken(user, jwt);

        var headers = new HttpHeaders();
        headers.setBearerAuth(jwt);
        return ResponseEntity.ok().headers(headers).build();
    }
}
