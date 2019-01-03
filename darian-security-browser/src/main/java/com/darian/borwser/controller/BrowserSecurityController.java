package com.darian.borwser.controller;

import com.darian.core.exception.CustomerReturnCode;
import com.darian.core.exception.Response;
import com.darian.core.log.LogCustom;
import com.darian.core.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@LogCustom(ignore = true)
@Slf4j
@Controller
@RequiredArgsConstructor
public class BrowserSecurityController {

    // 登陆前的缓存
    private RequestCache requestCache = new HttpSessionRequestCache();
    // 用来做页面的跳转
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final SecurityProperties securityProperties;
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;

    /***
     * 当需要身份认证时，跳转到这里
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping(value = "/authentication/require", produces = MediaType.TEXT_HTML_VALUE)
////    @GetMapping(value = "/authentication/require", produces = MediaType.TEXT_HTML_VALUE)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 没有认证登陆的 code
//    public void requirtAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response) throws IOException {
//        // 引发跳转请求的 URL
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//        if (savedRequest != null) {
//            String targetUrl = savedRequest.getRedirectUrl();
//            log.info("[引发跳转的请求是：]" + targetUrl + "\t[text/html]");
//            // 跳转到登陆页面
//            redirectStrategy.sendRedirect(request, response,
//                    securityProperties.getBrowser().getLoginPage());
//        }
//    }

    /***
     * 接收参数是 application/json;charset=UTF-8
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 没有认证登陆的 code
    @ResponseBody
    public Response requirtAuthenticationjson(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        // 其实这里边根本没有必要，打印 URL , 引发跳转请求的 URL
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("[引发跳转的请求是：]" + targetUrl + "\t[application/json;charset=UTF-8]");
        }
        return Response.error("请前去登陆",
                CustomerReturnCode.NEED_LOGIN);
    }

}
