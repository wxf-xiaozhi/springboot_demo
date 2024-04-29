package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TestFileUtil
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-28 16:00
 */
@Component
public class TestFileUtil1 {

    @Autowired
    private ResourceLoader resourceLoader;
    public  String getPath() {
        Resource resource = resourceLoader.getResource("classpath:testfile");
        return resource.getDescription().toLowerCase();
    }
}
