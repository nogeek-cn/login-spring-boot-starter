package com.darian.darianSecurityDemo.service.impl;

import com.darian.darianSecurityDemo.dto.User;
import com.darian.darianSecurityDemo.repository.UserRepository;
import com.darian.darianSecurityDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> users() {
        return userRepository.users();
    }

    @Override
    public User find(Long id) {
        return userRepository.find(id);
    }

    @Override
    public boolean add(User user) {
        return userRepository.add(user);
    }
}


