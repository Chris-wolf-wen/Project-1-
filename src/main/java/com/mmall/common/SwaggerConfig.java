package com.mmall.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo());
        ApiSelectorBuilder apiSelectorBuilder = docket.select();
        apiSelectorBuilder.apis(RequestHandlerSelectors.basePackage("com.mmall.controller"));
        //设置为上面basePackage包下面的哪些class生成接口信息
        apiSelectorBuilder.paths(PathSelectors.any());
        docket = apiSelectorBuilder.build();
        return docket;
    }

    private ApiInfo apiInfo(){
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("添加swagger来查看对外提供的接口信息");
        apiInfoBuilder.description("通过swagger来查看接口的具体信息，以及测试接口");
        apiInfoBuilder.version("1.0");
        ApiInfo apiInfo = apiInfoBuilder.build();
        return apiInfo;
    }
}