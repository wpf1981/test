package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
//    public Docket serviceDocket()    {
//    public Docket serviceDocket()  throws IOException {
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .useDefaultResponseMessages(false)
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.example"))//可以注释掉，显示所有接口，有自动生成的接口
                .paths(PathSelectors.any()) //所有路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3-Demo接口文档")
                .description("技术支持")
                .contact(new Contact("老武团队", "http://www.bdzjyy.com", "439300716@qq.com "))
                .version("1.0")
                .build();
    }
}
