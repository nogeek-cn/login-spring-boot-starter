//package com.darian.darianSercurityCore.properties;
//
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@ConfigurationProperties(prefix = "darian.security")
//public class SecurityProperties {
//
//    private BrowserProperties browser  = new BrowserProperties();
//
//    private ValidateCodeProperties code;
//
//
//    /***
//     * loginPage 登陆页
//     * remberMeSeconds “记住我” 的过期时间  单位： 秒
//     * 604800 s = 7 天
//     */
//    @Data
//    public static class BrowserProperties {
//        private String loginPage = "login";
//        private int remberMeSeconds = 604800;
//    }
//
//    /***
//     * 验证 字符 的配置
//     */
//    @Data
//    public static class ValidateCodeProperties {
//
//        private ImageCodeProperties image = new ImageCodeProperties();
//
//        private Set<String> not_validate_urls = new HashSet<>(Arrays.asList("/login"));
//
//
//        /***
//         * 验证码的参数相关配置
//         */
//        @Data
//        public static class ImageCodeProperties {
//            private int width = 67;
//            private int height = 23;
//            private int length = 4;
//            /***
//             * 过期时间
//             */
//            private int explireIn = 60;
//
//
//        }
//    }
//
//}