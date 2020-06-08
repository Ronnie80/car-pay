package com.yangyl.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyl
 * @date 2020年5月16日
 * swagger 配置
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<Parameter>();
        tokenBuilder.name("Authorization")
                .defaultValue("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0NDE5MTM4MCwidXNlciI6eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsInBhc3N3b3JkIjoiZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2UiLCJkZXB0SWQiOjEsImNyZWF0ZVRpbWUiOjE1MjQxNzk3MTgwMDAsInVwZGF0ZVRpbWUiOjE1NDM1NDU3OTEwMDAsImRlbEZsYWciOiIwIiwic2V4IjoxLCJ1c2VyVHlwZSI6MSwiYmlydGhkYXkiOiIyMDE4LTAxLTEyIiwiZnVsbE5hbWUiOiJhZG1pbiIsInJlbWFyayI6bnVsbCwicm9sZUlkIjpudWxsfX0.u2vg5vD0JZ3KCeGO9y0fRwnVlY12p8HzX-TGMl6WjjRAwErTlL4iBlierXpnuVQTpPh2UiIzFOcVry8bYLUBPA")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();
        parameterList.add(tokenBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yangyl.manage.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("管理 Swagger API ")
                .description("https://baidu.com")
                .termsOfServiceUrl("https://baidu.com")
                .contact(new Contact("杨永林","https://baidu.com","wangiegie@gmail.com"))
                .version("1.0")
                .build();
    }

}