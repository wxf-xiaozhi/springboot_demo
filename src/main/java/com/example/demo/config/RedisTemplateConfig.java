package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ClassName: RedisTemplateConfig
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-08-17 11:41
 */
@Configuration
@Slf4j
public class RedisTemplateConfig {

//    @Bean
//        public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }

    @Autowired
    ClusterConfigurationProperties clusterProperties;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(
                new RedisClusterConfiguration(clusterProperties.getNodes())
        );
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("redisTemplate build start");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        log.info("redisTemplate build end");
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("stringRedisTemplate build start");
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        log.info("stringRedisTemplate build end");
        return template;
    }
}
