package com.darian.demo.service.impl;

import com.darian.demo.dto.User;
import com.darian.demo.repository.UserRepository;
import com.darian.demo.service.UserService;
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


