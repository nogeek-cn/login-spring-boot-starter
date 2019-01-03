package com.darian.demo.service;

import com.darian.demo.dto.User;

import java.util.List;

public interface UserService {

    List<User> users();

    User find(Long id);

    boolean add(User user);
}
