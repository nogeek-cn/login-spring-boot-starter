package com.darian.borwser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginEntity implements UserDetails {
    private Long id;
    private String username;
    private String password;

    /***
     * 这个控制的是权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // 过期 true 就是 没有过期
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /***
     * 账号是不是锁定了，账号是不是锁定了
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /***
     * 密码是不是过期了
     *
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /***
     * 账号是不是可用，被删除的用户一般是不能恢复的
     * @return
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
