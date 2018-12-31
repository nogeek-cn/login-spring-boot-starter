package com.darian.darianSecurityDemo.service;

import com.darian.darianSecurityDemo.dto.User;

import java.util.List;

public interface UserService {

    List<User> users();

    User find(Long id);

    boolean add(User user);
}
