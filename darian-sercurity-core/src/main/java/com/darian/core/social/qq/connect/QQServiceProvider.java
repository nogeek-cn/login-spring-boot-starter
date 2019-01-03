package com.darian.core.social.qq.connect;

import com.darian.core.social.qq.api.QQ;
import com.darian.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private String appId;

    // 这个就是 QQ 服务上的地址
    private static final String URL_AUTHORIZE =
            "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN =
            "https://graph.qq.com/oauth2.0/token";

    /***
     * 你在 QQ 互联 上注册时时候 QQ 会给灭一个用户注册一个
     * clientId 和 clientSecret
     * 每一个应用再用我们模块的时候，它们各自应用的 appId 和 appSecret 是不一样的
     * 需要他们自己去配置。
     *
     * authorizeUrl 就是把用户导向服务器的时候，那个 URL,
     * accessTokenUrl 拿到授权码以后，用来获取 Token 的 URl
     */
    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
