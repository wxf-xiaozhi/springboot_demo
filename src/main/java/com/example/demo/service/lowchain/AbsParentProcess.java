package com.example.demo.service.lowchain;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AbsProcess
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-06-11 23:28
 */
@Slf4j
@Component
public abstract class AbsParentProcess implements IProcess{

    @Override
    public void process() {
        log.info("process");
        if (ObjectUtil.isNull(this.getNext())) {
            return;
        }
        // 在面向对象编程中，Java 等语言中有一个重要的概念：多态性（Polymorphism）。
        // 当子类调用父类的方法时，如果该方法中使用了 this 关键字，this 指代的是当前调用该方法的对象实例，而不是声明该方法的类。这意味着在运行时，this 会指向实际的对象实例，即子类的实例
        this.getNext().process();
    }

    public AbsParentProcess getNext(){
        log.info(" abs next");
        return null;
    };
}
