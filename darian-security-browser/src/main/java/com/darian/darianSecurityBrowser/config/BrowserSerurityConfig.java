package com.darian.darianSecurityBrowser.config;

import com.darian.darianSecurityBrowser.authentication.DarianAuthenticationSuccessHandler;
import com.darian.darianSercurityCore.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/***
 * web 安全配置
 */
@Configuration
@RequiredArgsConstructor
public class BrowserSerurityConfig extends WebSecurityConfigurerAdapter {
    //    private final ValidateCodeFilter validateCodeFilter;
    private final SecurityProperties securityProperties;
//    private final DataSource dataSource;
//    private final UserDetailsService myUerDetailsService;

    private final DarianAuthenticationSuccessHandler darianAuthenticationSuccessHandler;

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单登陆
        http.formLogin()  // 表单   // 用户名，密码验证前需要做的事情
//                http.httpBasic()   // 页面的登陆
                .loginPage("/authentication/require") // 一个不需要表单验证的页面
                .loginProcessingUrl("/authentication/form")
                .successForwardUrl("/onSuccessUserNamePasswordLogin")   // 我配置了成功以后的 URL 转发路径就不配置成功处理器了
                .failureForwardUrl("/onFailUserNamePasswordLogin")
//                .successHandler(darianAuthenticationSuccessHandler) // 表单通过以后，用我们的成功处理器来处理了
                .and()
//                .rememberMe()          // 记住我的链接
//                .tokenRepository(persistentTokenRepository())  //
//                .userDetailsService(myUerDetailsService)   // 登陆
//                .and()
                .authorizeRequests()    // 请求
                .antMatchers("/authentication/require").permitAll()  // 这里也需要设置
                .antMatchers(securityProperties.getBrowser().getLoginPage()).permitAll()
                .antMatchers("/onFailUserNamePasswordLogin").permitAll()
                .antMatchers("/login-success.html").permitAll()
                .antMatchers("/img/*").permitAll()
                .antMatchers("/js/*").permitAll()
                .anyRequest()           // 所有请求
                .authenticated()       // 都需要身份认证
                .and()
                .csrf().disable();// 先把跨站请求防护关掉
    }


//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        // 第一次启动设置这个为 true 执行建表语句
////        jdbcTokenRepository.setCreateTableOnStartup(true);
//        return jdbcTokenRepository;
//    }
}
