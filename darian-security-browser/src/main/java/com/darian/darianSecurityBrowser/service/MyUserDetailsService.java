package com.darian.darianSecurityBrowser.service;


import com.darian.darianSecurityBrowser.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserLoginRepository userLoginRepository;
    private final Environment env;
    private final static String SERVER_ENVIRONMENT_ONLY = "only";
    private final PasswordEncoder passwordEncoder;


    /***
     * 这个方法处理我自己的查询用户信息的逻辑
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("Security 开始查询用户信息");
        UserDetails userDetails = null;
        if (SERVER_ENVIRONMENT_ONLY.equals(env.getProperty("darian.server.environment"))) {
            log.info("单机环境");
            // 如果是本机环境
            // 姓名，姓名做验证
            // authorities 控制的是权限
            log.info("[前台传进来的用户名]：" + "root");
            log.info("[前台传进来的密码]：" + "root");
            String dbPassword = passwordEncoder.encode("root");
            log.info("[密码加密以后的String]" + dbPassword);
            User user = new User("root", dbPassword,
                    true, true, true, true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
            );

            log.info("用户信息：" + user);
            return user;

        } else {
            log.info("非单机环境。。。");
            userDetails = userLoginRepository.loadUserByUserName(s);
            log.info("用户信息：" + userDetails);
            return userDetails;
        }
    }
}
