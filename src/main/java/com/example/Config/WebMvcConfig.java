package com.example.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //把本地静态资源映射到项目
        try {
            registry.addResourceHandler("/files/**").addResourceLocations("file:D:/upload/");
            // 也可以映射项目里的static路径
           /*
				classpath:/resources/
				classpath:/static/
				classpath:/public/
				classpath:/META-INF/resources/
		*/
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("错误映射:"+e);
        }
    }
}
