package com.darian.borwser.repository;

import com.darian.borwser.dto.UserLoginEntity;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserLoginRepository {
    private Map<Long, UserLoginEntity> userMap = new ConcurrentHashMap<>();

    public List<UserLoginEntity> users() {
        return new ArrayList<>(userMap.values());
    }

    public UserLoginEntity find(Long id) {
        if (id == null)
            new ArrayList<>(userMap.values()).get(new Random().nextInt(userMap.size()));
        return userMap.get(id);
    }

    public boolean add(UserLoginEntity user) {
        if (user == null) {
            user = new UserLoginEntity();
        }
        if (user.getId() == null)
            user.setId(new Random().nextLong());
        if (!StringUtils.hasText(user.getUsername()))
            user.setUsername("root");
        if (!StringUtils.hasText(user.getPassword()))
            user.setPassword("root");
        return userMap.putIfAbsent(user.getId(), user) == null;
    }

    /***
     * 根据用户名查找用户信息
     * @param s
     * @return
     */
    public UserLoginEntity loadUserByUserName(String s) {
        return userMap.values().stream()
                .filter(user -> s.equals(user.getUsername()))
                .findFirst().get();
    }

    public SocialUserDetails loadUserByUserId(String userId) {
        //.. 查找用户信息。
        return null;
    }
}
