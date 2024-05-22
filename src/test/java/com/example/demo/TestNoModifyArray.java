package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: TestNoModifyArray
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-22 18:14
 */
public class TestNoModifyArray {
    public static void main(String[] args) {
        Integer[] arr = {9,15,12};
        List<Integer> collect =
                Arrays.stream(arr).collect(Collectors.toList());

        /**
         * 此方法不是深copy,往collect添加元素，integers里面也会同步添加
         * 只是不能单独往integers里面添加，否则抛异常
         */
        List<Integer> integers = Collections.unmodifiableList(collect);
        collect.add(90);
        System.out.println("可变："+collect);
        System.out.println("不可变:"+integers);
        // 会抛异常
        integers.add(91);
        System.out.println("不可变:"+integers);




// 创建包含4个元素的Set集合
//                Set set = Set.of("Java", "Kotlin", "Go", "Swift");
//                System.out.println(set);
// 不可变集合，下面代码导致运行时错误
// set.add("Ruby");
// 创建包含4个元素的List集合

//                List list = List.of(34, -25, 67, 231);
//                System.out.println(list);
// 不可变集合，下面代码导致运行时错误
// list.remove(1);
// 创建包含3个key-value对的Map集合

//                Map map = Map.of("语文", 89, "数学", 82, "英语", 92);
//                System.out.println(map);
// 不可变集合，下面代码导致运行时错误
// map.remove("语文");
// 使用Map.entry()方法显式构建key-value对

//                Map map2 = Map.ofEntries(Map.entry("语文", 89), Map.entry("数学", 82), Map.entry("英语", 92));
//                System.out.println(map2);


    }
}
