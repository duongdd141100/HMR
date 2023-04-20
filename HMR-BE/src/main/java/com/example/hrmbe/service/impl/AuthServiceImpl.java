package com.example.hrmbe.service.impl;

import com.example.hrmbe.common.ErrorMessageEnum;
import com.example.hrmbe.entity.User;
import com.example.hrmbe.repository.UserRepository;
import com.example.hrmbe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.CharBuffer;
import java.util.Arrays;

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

    @Override
    public User save(User user) {
        if (Arrays.asList(user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getRoles()).stream().anyMatch(StringUtils::hasText)
                && user.getGender() != null
                && user.getDob() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
        throw new RuntimeException(ErrorMessageEnum.LACK_OF_INFORMATION.getCode());
    }
}
