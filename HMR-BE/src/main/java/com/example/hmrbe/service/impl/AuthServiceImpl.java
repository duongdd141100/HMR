package com.example.hmrbe.service.impl;

import com.example.hmrbe.common.ErrorMessageEnum;
import com.example.hmrbe.entity.User;
import com.example.hmrbe.repository.UserRepository;
import com.example.hmrbe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.CharBuffer;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User validateUser(User user) {
        if (user != null && StringUtils.hasText(user.getEmail())) {
            User realUser = userRepo.findByEmail(user.getEmail());
            if (passwordEncoder.matches(CharBuffer.wrap(user.getPassword()), realUser.getPassword())) {
                return realUser;
            }
        }
        throw new RuntimeException(ErrorMessageEnum.LOGIN_FAILED.getCode());
    }

    @Override
    public User findByEmail(String email) {
        if (StringUtils.hasText(email)) {
            return userRepo.findByEmail(email);
        }
        throw new RuntimeException(ErrorMessageEnum.TOKEN_INVALID.getCode());
    }
}
