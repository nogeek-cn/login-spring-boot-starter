package com.darian.darianSecurityBrowser.controllerConfig;

import com.darian.darianSercurityCore.exception.CustomException;
import com.darian.darianSercurityCore.exception.CustomerReturnCode;
import com.darian.darianSercurityCore.exception.Response;
import com.darian.darianSercurityCore.log.LogCustom;
import com.darian.darianSercurityCore.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.darian.darianSercurityCore.exception.CustomerReturnCode.ERR_APPLICATION_OPER_ERROR;
import static com.darian.darianSercurityCore.exception.Response.error;
import static com.darian.darianSercurityCore.exception.Response.success;

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
    @RequestMapping(value = "/authentication/require", produces = MediaType.TEXT_HTML_VALUE)
//    @GetMapping(value = "/authentication/require", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 没有认证登陆的 code
    public void requirtAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        // 引发跳转请求的 URL
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("[引发跳转的请求是：]" + targetUrl + "\t[text/html]");
            // 跳转到登陆页面
            redirectStrategy.sendRedirect(request, response,
                    securityProperties.getBrowser().getLoginPage());
        }
    }

    /***
     * 接收参数是 application/json;charset=UTF-8
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/authentication/require", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping(value = "/authentication/require", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(value = "/onFailUserNamePasswordLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping(value = "/onFailUserNamePasswordLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response onFailUserNamePasswordLogin(AuthenticationException e) {
        log.error("login ... error"+ "\t[application/json;charset=UTF-8]");
        if (true) {
            throw CustomException.generatorRuntimeException("登陆失败！！！");
        }
        return error(e, ERR_APPLICATION_OPER_ERROR);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(value = "/onFailUserNamePasswordLogin", produces = MediaType.TEXT_HTML_VALUE)
//    @GetMapping(value = "/onFailUserNamePasswordLogin", produces = MediaType.TEXT_HTML_VALUE)
    public String onFailUserNamePasswordLoginHtml(AuthenticationException e, Model model) {
        log.error("login ... error"+ "\t[text/html]");
//        model.addAttribute("e", e);
        return "500";
    }
    @ResponseBody
    @PostMapping(value = "/onSuccessUserNamePasswordLogin",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response onSuccessUserNamePasswordLogin(Authentication authentication) {
        log.info("login success....."+ "\t[application/json;charset=UTF-8]");
        return success(authentication);
    }

    @PostMapping(value = "/onSuccessUserNamePasswordLogin",
            produces = MediaType.TEXT_HTML_VALUE)
    public String onSuccessUserNamePasswordLoginHtml(Authentication authentication) {
        log.info("login success....."+ "\t[text/html]");
        return "login-success";
    }

}
