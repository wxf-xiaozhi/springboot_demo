package com.example.demo.feign;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 * (一句话描述该类的功能)
 *
 * @version v1.0
 * @Author: xiaozhi
 * @Date: 2023/12/7 22:34
 */
@Slf4j
public class CustomFeignLogger extends Logger {



    @Override
    protected boolean shouldLogRequestHeader(String header) {
        return false;
    }


    @Override
    protected boolean shouldLogResponseHeader(String header) {
        return false;
    }




    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info(String.format(methodTag(configKey) + format, args));


    }
    /**
     * feign日志级别
     *
     * @version v1.0
     * @Author: xiaozhi
     * @Date: 2023/12/7 23:04
     */
    public enum FeignLoggerLevelEnum {
        INFO,
        DEBUG;
    }
}
