package com.example.demo.service.chain.node;

import com.example.demo.service.chain.AbsPush;
import lombok.Data;

/**
 * (一句话描述该类的功能)
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/11 11:30
 */
@Data
public class DefaultPushNode{

    AbsPush value;

    DefaultPushNode next;

    public DefaultPushNode(AbsPush value) {
        this.value = value;
    }
}
