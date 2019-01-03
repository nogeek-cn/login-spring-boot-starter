package com.darian.core.social;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


@Configuration
@EnableSocial
@RequiredArgsConstructor
public class ScoialConfig extends SocialConfigurerAdapter {
    private final DataSource dataSource;

    /***
     * ConnectionFactoryLocator
     *
     * @param connectionFactoryLocator 就是去查找我的 ConnectionFactory ,
     *                                 我系统里边会有很多 ConnectioinFactory
     *
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //  Encryptors : 用来加解密
        // 我们这里并没有加解密
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // 可以设置前缀
//        repository.setTablePrefix("");
        return repository;
    }

    @Bean
    public SpringSocialConfigurer darianSocialConfigurer(){
        return new SpringSocialConfigurer();
    }
}