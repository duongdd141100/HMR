package com.example.hrmbe.config;

import com.example.hrmbe.constants.Constants;
import com.example.hrmbe.constants.RequestMappingConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private FilterExceptionHandler filterExceptionHandler;

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Autowired
    private UsernamePasswordAuthFilter usernamePasswordAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .addFilterBefore(usernamePasswordAuthFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthFilter.class)
                .addFilterBefore(filterExceptionHandler, JWTAuthFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(HttpMethod.POST, RequestMappingConstant.SIGN_IN_API, RequestMappingConstant.SIGN_UP_API, RequestMappingConstant.SIGN_OUT_API).permitAll()
                            .anyRequest().authenticated();
                });
        return http.build();
    }

}
