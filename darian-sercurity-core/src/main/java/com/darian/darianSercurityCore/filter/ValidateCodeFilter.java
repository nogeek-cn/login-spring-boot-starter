//package com.darian.darianSercurityCore.filter;
//
//import com.darian.darianSercurityCore.properties.SecurityProperties;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Set;
//
///***
// *
// * 验证图片，验证码，手机验证码，等逻辑。
// * @author Darian
// */
//@RequiredArgsConstructor
//@Component
//public class ValidateCodeFilter extends OncePerRequestFilter {
//
//    private final AntPathMatcher antPathMatcher;
//
//    private final SecurityProperties securityProperties;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
//        // 调用后边的逻辑
//        filterChain.doFilter(request, response);
//    }
//}
