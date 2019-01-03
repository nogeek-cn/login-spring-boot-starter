package com.darian.core.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 *
 * 验证图片，验证码，手机验证码，等逻辑。
 * @author Darian
 */
@RequiredArgsConstructor
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    //    private final AntPathMatcher antPathMatcher;
//
//    private final SecurityProperties securityProperties;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // 登陆的验证的
//        Set<String> urls = securityProperties.getCode().getNot_validate_urls();
//
//        Boolean action = urls.stream()
//                .filter(url ->
//                        antPathMatcher.match(url, request.getRequestURI()))
//                .findFirst().map(s -> true).orElse(false);
//
//        if (action && "post".equals(request.getMethod())) {
//
//        }
        if (StringUtils.pathEquals("/authentication/form", request.getRequestURI())
                && StringUtils.pathEquals(request.getMethod().toLowerCase(), "post")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        // 如果，不是，登陆的逻辑，我直接调用后边的逻辑，调用后边的逻辑
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException, ValidateCodeException {

        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request,
                ValidateCodeController.SESSION_KEY_IMAGE_CODE);

//        C codeInSession = (C) validateCodeRepository.get(request, codeType);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                "imageCode");

//        String codeInRequest;
//        try {
//            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
//                    codeType.getParamNameOnValidate());
//        } catch (ServletRequestBindingException e) {
//            throw new ValidateCodeException("获取验证码的值失败");
//        }

        if (!StringUtils.hasText(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(/*codeType +*/ "验证码不存在");
        }

        if (codeInSession.isExpried()) {
//            validateCodeRepository.remove(request, codeType);
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.pathEquals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
//        validateCodeRepository.remove(request, codeType);

    }
}
