//package com.darian.darianSecurityDemo.configuratin;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//
//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        // 如果你想监听异步叫用，那么你就注册拦截器
////        configurer.registerDeferredResultInterceptors();
//        configurer.setDefaultTimeout(1000);
//        // 可以实则设置异步线程池
//        super.configureAsyncSupport(configurer);
//    }
//
//}
