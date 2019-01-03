package com.darian.demo.configuratin;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
//    private final TimeInterceptor timeInterceptor;
//    @Bean
//    public FilterRegistrationBean timeFilter(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new TimeFilter());
//        List<String> urls = new ArrayList<>();
//        urls.add("/*");
//        filterRegistrationBean.setUrlPatterns(urls);
//        return filterRegistrationBean;
//    }

//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        // 如果你想监听异步叫用，那么你就注册拦截器
////        configurer.registerDeferredResultInterceptors();
//        configurer.setDefaultTimeout(1000);
//        // 可以实则设置异步线程池
//        super.configureAsyncSupport(configurer);
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这里是否要添加过滤器
//        registry.addInterceptor(timeInterceptor);
        super.addInterceptors(registry);
    }
}
