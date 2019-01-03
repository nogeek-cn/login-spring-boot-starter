package com.darian.core.config;

import com.darian.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

    /***
     * 这个版本实例化 PasswordEncoder 的 Bean,
     * 也可以自己返回一个 PasswordEncoder 的实例
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
