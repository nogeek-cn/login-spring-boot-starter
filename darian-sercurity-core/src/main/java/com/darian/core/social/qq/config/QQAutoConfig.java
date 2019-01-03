package com.darian.core.social.qq.config;

import com.darian.core.properties.SecurityProperties;
import com.darian.core.social.qq.connect.QQConnectionFacotory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import static com.darian.core.properties.SecurityProperties.socialProperties.QQProperties;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "darian.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    private final SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();

        return new QQConnectionFacotory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
