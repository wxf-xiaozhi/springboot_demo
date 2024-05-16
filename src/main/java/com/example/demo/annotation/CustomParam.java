package com.example.demo.annotation;

/**
 * @ClassName: CustomParam
 * @description: 自定义参数解析器的标志注解
 * @author: xiaofang.wu
 * @create: 2024-05-16 17:17
 */

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface CustomParam {
}
