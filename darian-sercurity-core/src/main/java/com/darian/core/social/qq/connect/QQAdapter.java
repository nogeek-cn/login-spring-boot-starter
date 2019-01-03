package com.darian.core.social.qq.connect;

import com.darian.core.social.qq.api.QQ;
import com.darian.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {

    // 测试 服务器是否还是可用的。
    @Override
    public boolean test(QQ qq) {
        // 这里假定 QQ 服务永远是通的
        return true;
    }

    /***
     * 把个性化的信息放到了 ConnectionValues 上边
     * @param qq
     * @param connectionValues
     */
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInFo = qq.getUserInFo();
        connectionValues.setDisplayName(userInFo.getNickname());
        connectionValues.setImageUrl(userInFo.getFigureurl_qq_1());
        // 个人主页的 URL QQ 没有
        connectionValues.setProfileUrl(null);
        // 这个用户在服务商的为一个的标识 openId
        connectionValues.setProviderUserId(userInFo.getOpenId());

    }

    /***
     * 通过 API 拿到用户标准的用户信息
     * 绑定，解绑的时候具体的去写
     * @param qq
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    /***
     * 在某些社交网站上才会有这些概念
     * 比如说 微博，你是可以发一个消息去更新你的微博的。
     * 但是 QQ 是没有时间线啊，个人主页这种东西的。
     * @param qq
     * @param s
     */
    @Override
    public void updateStatus(QQ qq, String s) {
        // doNothing
    }
}
