package com.mfg.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * The configuration will enable the swagger page.
 *
 * @author Sand Wen
 * @since 2016.9.7
 */
@Configuration
@EnableSwagger2
@ComponentScan("com.mfg.*.endpoint")
public class SwaggerConfig {
    public static final String PUBLIC_API = "Public";
    @Bean
    public Docket publicAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(PUBLIC_API)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(regex("/api.*")))
                .build()
                .apiInfo(publicApiInfo());
    }
    private ApiInfo publicApiInfo() {
        ApiInfo apiInfo = new ApiInfo("MFG Rest API",
                "MFG Platform rest api",
                "1.0",
                "No terms of service",
                "sand.fj.wen@gmail.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }
}