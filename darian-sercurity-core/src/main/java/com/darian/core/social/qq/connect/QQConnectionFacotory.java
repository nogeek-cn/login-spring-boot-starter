package com.darian.core.social.qq.connect;

import com.darian.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFacotory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFacotory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}
