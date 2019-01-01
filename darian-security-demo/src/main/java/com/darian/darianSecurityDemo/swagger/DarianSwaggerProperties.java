package com.darian.darianSecurityDemo.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/***
 *
 * @author Darian
 */
@Configuration
@ConfigurationProperties(prefix = "com.darian.swagger")
@Data
public class DarianSwaggerProperties {

    /***
     * 标题
     */
    private String title = "darian-swagger-默认标题";

    /***
     * 版本
     */
    private String version = "1.0";

    /***
     * 扫描的包
     */
    private String[] selectorbasePackages = new String[]{""};
    /***
     * 备注信息
     */
    private String description;

    private ParameterProperties[] parameters = new ParameterProperties[]{};

    @Data
    public static class ParameterProperties {

        /***
         * 参数类型支持header, cookie, body, query etc
         */
        private String parameterType;
        /***
         * 参数名
         */
        private String name;
        /***
         * 默认值
         */
        private String defaultValue;

        /***
         * 这个字段的注释
         */
        private String description;

        /***
         * 指定参数值的类型
         */
        private String modelRef;

        /***
         * 是否必填
         **/
        private Boolean required;

    }

}
