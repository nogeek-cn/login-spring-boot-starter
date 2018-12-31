package com.darian.darianSecurityDemo.repository;

import com.darian.darianSecurityDemo.dto.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private Map<Long, User> userMap = new ConcurrentHashMap<>();

    public List<User> users() {
        return new ArrayList<>(userMap.values());
    }

    public User find(Long id) {
        if (id == null)
            new ArrayList<>(userMap.values()).get(new Random().nextInt(userMap.size()));
        return userMap.get(id);
    }

    public boolean add(User user) {
        if (user == null) {
            user = new User();
        }
        if (user.getId() == null)
            user.setId(new Random().nextLong());
        if (!StringUtils.hasText(user.getUsername()))
            user.setUsername("root");
        if (!StringUtils.hasText(user.getPassword()))
            user.setPassword("root");
        return userMap.putIfAbsent(user.getId(), user) == null;
    }
}
