package com.example.demo.context;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: MyContext
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-06-07 14:39
 */
@Data
public class MyContext implements Serializable {

    private String key;
}
