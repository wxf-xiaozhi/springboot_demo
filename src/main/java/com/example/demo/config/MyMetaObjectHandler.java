package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入填充...");
        DefaultIdentifierGenerator instance = DefaultIdentifierGenerator.getInstance();
        this.strictInsertFill(metaObject, "bid", String.class, instance.nextUUID(null));

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新填充...");
//        this.strictInsertFill(metaObject, "updateUserId", Long.class, 123456L)
//        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
