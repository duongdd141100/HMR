package com.example.hrmbe.controller;

import com.example.hrmbe.common.BaseResponse;
import com.example.hrmbe.common.ErrorMessageEnum;
import com.example.hrmbe.config.UserAuthProvider;
import com.example.hrmbe.constants.Constants;
import com.example.hrmbe.constants.RequestMappingConstant;
import com.example.hrmbe.entity.User;
import com.example.hrmbe.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserAuthProvider userAuthProvider;

    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse<User>> signUp(@RequestBody User user) {
        try {
            return ResponseEntity.ok(BaseResponse.ok(authService.save(user)));
        } catch (Exception e) {
            log.error(RequestMappingConstant.SIGN_UP_API + e);
            return ResponseEntity.badRequest().body(BaseResponse.fail(ErrorMessageEnum.typeOf(e.getMessage()).getMessage()));
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<BaseResponse<String>> signIn(@AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.ok(BaseResponse.ok(userAuthProvider.createToken(user.getEmail())));
        } catch (Exception e) {
            log.error(RequestMappingConstant.SIGN_IN_API + e);
            return ResponseEntity.badRequest().body(BaseResponse.fail(ErrorMessageEnum.typeOf(e.getMessage()).getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<String>> me(@AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.ok(BaseResponse.ok(user));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(BaseResponse.fail(ErrorMessageEnum.typeOf(e.getMessage()).getMessage()));
        }
    }

}
