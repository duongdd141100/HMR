package com.example.hrmbe.controller;

import com.example.hrmbe.common.BaseResponse;
import com.example.hrmbe.constants.Constants;
import com.example.hrmbe.entity.User;
import com.example.hrmbe.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse<User>> signUp(User user) {
        try {
            return ResponseEntity.ok(BaseResponse.ok(BaseResponse.ok(authService.save(user))));
        } catch (Exception e) {
            log.error(Constants.SIGN_UP_API + e);
            return ResponseEntity.badRequest().body(BaseResponse.fail(e.getMessage()));
        }
    }

}
