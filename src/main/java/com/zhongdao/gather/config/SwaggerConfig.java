package com.zhongdao.gather.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean

    public Docket createRestApi() {


        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/")
                .select().apis(RequestHandlerSelectors.basePackage("com.zhongdao.gather")).paths(PathSelectors.any()).
                        build().apiInfo(new ApiInfoBuilder().title("云集APP项目")
                        .description("云集APP项目接口").version("9.0").
                                contact( new Contact("友情链接", "blog.csdn.net", "aaa@gmail.com")).license("The Apache License").licenseUrl("http://www.baidu.com").build());


    }
}


