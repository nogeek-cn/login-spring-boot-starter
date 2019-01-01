package com.darian.darianSecurityBrowser.authentication;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 *
 * 成功之后的处理器
 * because I user this URL
 * so I Don't User this , so hard
 */
@Deprecated()
@Component
@Slf4j
@RequiredArgsConstructor
public class DarianAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;

    /***
     *
     * 我自定义个了一个成功处理器的行为
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication  封装我们的认证信息，认证信息包括我们的认证里边的一些
     *                        请求里边的信息，IP 和 Session 等
     *                        还有认证通过以后，UserDetail 信息也是包装在
     *                        Authentication 里边
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication)
            throws IOException, ServletException {
        log.info("登陆成功");
//        onSuccessLoginController.onSuccessLogin(authentication);
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
    }
}
