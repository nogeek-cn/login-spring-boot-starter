package com.darian.darianSecurityDemo.swagger;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
@EnableSwagger2
@AutoConfigureAfter(DarianSwaggerProperties.class)
@RequiredArgsConstructor
public class Swagger2Config {
    private final DarianSwaggerProperties darianSwaggerProperties;

    @Autowired
    private Environment env;

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {

        List<Parameter> pars = Stream.of(darianSwaggerProperties.getParameters()).map(parameterProperties ->
                new ParameterBuilder().parameterType(parameterProperties.getParameterType())
                        .name(parameterProperties.getName())
                        .defaultValue(parameterProperties.getDefaultValue())
                        .description(parameterProperties.getDescription())
                        .modelRef(new ModelRef(parameterProperties.getModelRef()))
                        .required(parameterProperties.getRequired()).build())
                .collect(Collectors.toList());

        Optional<DarianSwaggerProperties.ParameterProperties> first = Stream.of(darianSwaggerProperties.getParameters())
                .filter(parameterProperties -> {
                    String parameterType = parameterProperties.getParameterType();
                    String name = parameterProperties.getName();
                    return "header".equals(parameterType) && "Accept-Language".equals(name);
                }).findFirst();

        if (!first.isPresent()) {
            Parameter languageParameter = new ParameterBuilder()
                    .parameterType("header") //参数类型支持header, cookie, body, query etc
                    .name("Accept-Language") //参数名
                    .defaultValue("zh-CN") //默认值
                    .description("用户可以接收的语言")
                    .modelRef(new ModelRef("string"))//指定参数值的类型
                    .required(false).build();//非必需，这里是全局配置
            pars.add(languageParameter);
        }


        ApiSelectorBuilder select = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select();


        Stream.of(darianSwaggerProperties.getSelectorbasePackages()).forEach(basePackage ->
                select.apis(RequestHandlerSelectors.basePackage(basePackage))
                        .paths(PathSelectors.any())
        );

        Docket docket = select.build();

        if (pars.size() > 0)
            docket.globalOperationParameters(pars);
        return docket;
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/ swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(darianSwaggerProperties.getTitle())
                .description("备注:  \n   " +
                        "本程序现在运行时地址 : http://" + IPUtils.getRealIp() + ":" + getPort())
                .termsOfServiceUrl("http://" + IPUtils.getRealIp() + ":" + getPort())
                .version(darianSwaggerProperties.getVersion())
                .build();
    }


    private String getPort() {
        String serverPort = env.getProperty("server.port");
        if (serverPort == null)
            return "8080";
        return serverPort;
    }

}