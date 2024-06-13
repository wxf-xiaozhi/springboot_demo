# 此项目为验证各种框架在springboot集成效果的项目

### 当前继承三方框架有

## easyExcel
##### 主要测试自定义convert,编写各个接口导入的字段的通用convert
```agsl
com.example.demo.EasyExcelTest
```
##### 测试easyExcel的限制某些字段导出
```agsl
com.example.demo.EasyExcelTest.indexWrite
```

## liteflow，主要测试自定义业务引擎写法
```agsl
com.example.demo.controller.TestController.testConfig
```

## guava-retrying 主要测试重试框架中根据每次重试结果的自定义逻辑添加
```agsl
com.example.demo.RetryTest.main
```

## 测试容器启动时找不到properties文件时，是否会报错，报的什么错
```agsl
src/main/java/com/example/demo/AliPayBean.java
```

## 测试stopwatch用发
```agsl
com/example/demo/StopWatchTest.java
```

## 基于角色的数据列级别的控制（列级别控制）
### sql列级别过滤
### 序列化级别基于字段的过滤
```agsl
com.example.demo.aspect.DataFilterAspect.dataFilter
```

## 多态下this测试,简单链式调用实现
```agsl
com.example.demo.AbsParentProcessTest
```

## 链式调用实现
```
com.example.demo.service.chain.ZlPushService
```

