//package com.darian.darianSecurityBrowser.config;
//
//import com.darian.darianSercurityCore.filter.ValidateCodeFilter;
//import com.darian.darianSercurityCore.properties.SecurityProperties;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.sql.DataSource;
//
///***
// * web 安全配置
// */
//@Configuration
//@RequiredArgsConstructor
//public class BrowserSerurityConfig extends WebSecurityConfigurerAdapter {
//    private final ValidateCodeFilter validateCodeFilter;
//    private final SecurityProperties securityProperties;
//    private final DataSource dataSource;
//    private final UserDetailsService myUerDetailsService;
//
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 表单登陆
////        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)  // 用户名，密码验证前需要做的事情
////                .formLogin()   // 表单
////                .loginPage("/login") // 一个不需要表单验证的页面
////                .and()
////                .rememberMe()          // 记住我的链接
////                .tokenRepository(persistentTokenRepository())  //
////                .tokenValiditySeconds(
////                        securityProperties.getBrowser()
////                                .getRemberMeSeconds()) // 过期时间
////                .userDetailsService(myUerDetailsService)   // 登陆
////                .and()
////                .authorizeRequests()    // 请求
////                .antMatchers("/login").permitAll()  // 这里也需要设置
////                .anyRequest()           // 所有请求
////                .authenticated();       // 都需要身份认证
//    }
//
//
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        // 第一次启动设置这个为 true 执行建表语句
////        jdbcTokenRepository.setCreateTableOnStartup(true);
//        return jdbcTokenRepository;
//    }
//}
