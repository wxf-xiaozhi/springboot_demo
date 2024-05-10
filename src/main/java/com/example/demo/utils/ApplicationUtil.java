package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ningning.cheng
 * @date 2022/02/11
 **/
@Component
public class ApplicationUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationUtil.init(context);
    }

    public static void init(ApplicationContext context) {

        synchronized (ApplicationUtil.class) {
            ApplicationUtil.applicationContext = context;
        }
    }

    /**
     * 获取Bean
     *
     * @param clazz
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.<T>getBean(clazz);
    }
}
