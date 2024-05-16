package com.example.demo.config;

import com.example.demo.param.resolver.CustomParamResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ClassName: CustomWebMvcConfigurer
 * @description: 自定义参数解析器
 * @author: xiaofang.wu
 * @create: 2024-05-16 17:15
 */
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CustomParamResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);

    }

}