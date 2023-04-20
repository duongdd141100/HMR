package com.example.hmrbe.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.hmrbe.entity.User;
import com.example.hmrbe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserAuthProvider {

    @Autowired
    private AuthService authService;

    private final String secretKey = "hrm-system";

    public Authentication validateUser(User user) {
        return new UsernamePasswordAuthenticationToken(authService.validateUser(user), null, Collections.emptyList());
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return new UsernamePasswordAuthenticationToken(authService.findByEmail(decodedJWT.getIssuer()), null, Collections.emptyList());
    }
}
