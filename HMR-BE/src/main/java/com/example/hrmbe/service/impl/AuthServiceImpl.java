package com.example.hrmbe.service.impl;

import com.example.hrmbe.common.Converter;
import com.example.hrmbe.common.ErrorMessageEnum;
import com.example.hrmbe.constants.Constants;
import com.example.hrmbe.entity.User;
import com.example.hrmbe.repository.UserRepository;
import com.example.hrmbe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
                user.getPassword(),
                user.getPhoneNumber(),
                user.getRoles()).stream().allMatch(StringUtils::hasText)
                && user.getGender() != null
                && user.getDob() != null) {
            List<String> names = Arrays.asList(Converter.removeAccent(user.getFullName().trim().toLowerCase()).split(" "));
            StringBuilder username = new StringBuilder(names.get(names.size() - 1));
            for (int i = 0; i < names.size() - 1; i++) {
                username.append(names.get(i).charAt(0));
            }
            boolean isExist = true;
            while (isExist) {
                Random random = new Random();
                String email = username.toString() + random.nextInt(100) + Constants.PREFIX;
                isExist = userRepo.findByEmail(email) != null;
                if (!isExist) {
                    user.setEmail(email);
                }
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        }
        throw new RuntimeException(ErrorMessageEnum.LACK_OF_INFORMATION.getCode());
    }
}
