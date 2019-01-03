package com.darian.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;


public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID =
            "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /***
     * 官方实例上面写
     * https://graph.qq.com/user/get_user_info?access_token=YOUR_ACCESS_TOKEN
     * &oauth_consumer_key=YOUR_APP_ID&openid=YOUR_OPENID
     * 而 access_token 会由我的父类去处理，所以就不写了
     */
    private static final String URL_GET_USERINFO =
            "https://graph.qq.com/user/get_user_info?" +
                    "&oauth_consumer_key=%s" +
                    "&openid=%s";


    private String appId;

    private String openId;


    /***
     * TokenStrategy.AUTHORIZATION_HEADER 策略会把 Token 放到头里边
     * TokenStrategy.ACCESS_TOKEN_PARAMETER QQ 要求把 ACCESS_TOKEN 放到 paramter 里边给传递进去
     * 所以我们把采用 TokenStrategy.ACCESS_TOKEN_PARAMETER 策略。
     *
     * @param accessToken
     * @param appId
     */
    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        //
        String result = getRestTemplate().getForObject(url, String.class);
        // 从返回值中取出来 openId
        this.openId = StringUtils.substringBetween(result, "\"openid\"", "}");
    }


    @Override
    public QQUserInfo getUserInFo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        return getRestTemplate().getForObject(url, QQUserInfo.class);
    }
}
