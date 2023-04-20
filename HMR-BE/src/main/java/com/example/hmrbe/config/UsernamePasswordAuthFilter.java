package com.example.hmrbe.config;

import com.example.hmrbe.common.ErrorMessageEnum;
import com.example.hmrbe.constants.Constants;
import com.example.hmrbe.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (Constants.SIGN_IN_API.equals(request.getServletPath())
                && HttpMethod.POST.matches(request.getMethod())) {
            try {
                User user = objectMapper.readValue(request.getInputStream(), User.class);
                SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateUser(user));
            } catch (Exception e) {
                throw new RuntimeException(ErrorMessageEnum.LOGIN_FAILED.getCode());
            }
        }
        filterChain.doFilter(request, response);
    }
}
