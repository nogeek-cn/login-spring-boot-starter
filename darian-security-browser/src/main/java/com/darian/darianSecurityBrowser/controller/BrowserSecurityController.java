//package com.darian.darianSecurityBrowser.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//
//@Slf4j
//@Controller
//public class BrowserSecurityController {
//
////    // 登陆前的缓存
////    private RequestCache requestCache = new HttpSessionRequestCache();
////    // 用来做页面的跳转
////    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
////    @Autowired
////    private MyUserDetailsService myUserDetailsService;
////    /***
////     * 当需要身份认证时，跳转到这里
////     * @param request
////     * @param response
////     * @return
////     */
////    @RequestMapping("/needLogin")
////    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 没有认证登陆的 code
////    public ApiResponse requirtAuthentication(
////            HttpServletRequest request,
////            HttpServletResponse response) throws IOException {
////        // 引发跳转请求的 URL
////        SavedRequest savedRequest = requestCache.getRequest(request, response);
////        if (savedRequest != null) {
////            String targetUrl = savedRequest.getRedirectUrl();
////            log.info("[引发跳转的请求是：]" + targetUrl);
////            // 跳转到登陆页面
////            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")){
////                redirectStrategy.sendRedirect(request, response, "login");
////            }
////        }
////        return apiResponseError(null,
////                CustomerReturnCode.NEED_LOGIN);
////    }
////
////    public ApiResponse loginFirst(UserLogin userLogin){
////        if(userLogin)
////    }
//}
