package com.example.hmrbe.service;

import com.example.hmrbe.entity.User;

public interface AuthService {
    User validateUser(User user);
}
