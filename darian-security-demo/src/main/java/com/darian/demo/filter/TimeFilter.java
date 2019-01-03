package com.darian.demo.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang.builder.ToStringStyle.MULTI_LINE_STYLE;


//@Component
@Slf4j
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("My TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("My TimeFilter start");
        long start = currentTimeMillis();
        log.info("servletRequest:" + ReflectionToStringBuilder.toString(servletRequest, MULTI_LINE_STYLE));
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
//        log.info("requestURI:" + servletRequest1.getRequestURI());
//        log.info("HTTPservletRequest:" +
//                ReflectionToStringBuilder.toString(servletRequest1, MULTI_LINE_STYLE));

        filterChain.doFilter(servletRequest, servletResponse);
        long end = currentTimeMillis();
        log.info("this method 耗时 cost Time: " + (end - start));
        log.info("My TimeFilter finshed");
    }

    @Override
    public void destroy() {
        log.info("My TimeFilter destory");
    }

}