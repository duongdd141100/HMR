package com.example.hrmbe.service;

import com.example.hrmbe.entity.User;

public interface AuthService {
    User validateUser(User user);

    User findByEmail(String email);

    User save(User user);
}
