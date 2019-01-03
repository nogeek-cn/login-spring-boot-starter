package com.darian.demo.configuratin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang.builder.ToStringStyle.MULTI_LINE_STYLE;

//@Component   // 如果使用，加上去
@Slf4j
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
        log.info(ReflectionToStringBuilder.toString(handlerMethod, MULTI_LINE_STYLE));
        request.setAttribute("startTime", currentTimeMillis());
        return true;// 这个值决定你是不是要调用后边的方法
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        long startTime = (Long) request.getAttribute("startTime");
        log.info("time Interceptor 耗时：" + (currentTimeMillis() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
        long startTime = (Long) request.getAttribute("startTime");
        log.info("time Interceptor 耗时：" + (currentTimeMillis() - startTime));
    }
}
