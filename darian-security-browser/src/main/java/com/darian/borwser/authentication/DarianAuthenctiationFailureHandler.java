/**
 *
 */
package com.darian.borwser.authentication;

import com.darian.core.exception.CustomerReturnCode;
import com.darian.core.exception.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 浏览器环境下登录失败的处理器
 *
 * @author zhailiang
 */
@Slf4j
@Component
public class DarianAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("cause", exception.getCause());
        errorMap.put("localizedMessage", exception.getLocalizedMessage());
        errorMap.put("message", exception.getMessage());
        errorMap.put("suppressed", exception.getSuppressed());
        String returnValue = objectMapper.writeValueAsString(
                Response.error(errorMap, CustomerReturnCode.ERR_SERVICE_UNKNOWN_ERROR));
        response.getWriter().write(returnValue);

    }
}
