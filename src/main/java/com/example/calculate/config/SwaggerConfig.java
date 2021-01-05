package com.example.calculate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * springboot使用swagger2的配置
 * 
 * swagger-bootstrap-ui默认访问地址是：http://${host}:${port}/doc.html
 */
//@Profile("dev")
//开发环境启动接口文档，生产环境不启用
@Configuration
@Profile("dev")
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.calculate.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api文档")
                .description("简单优雅的restful风格\nyls")
                .termsOfServiceUrl("https://doc.xiaominfo.com/knife4j/")
                .version("1.0")
                .build();
    }
}
