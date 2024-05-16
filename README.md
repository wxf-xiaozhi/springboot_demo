# 此项目为验证各种框架在springboot集成效果的项目

### 当前继承三方框架有

#### easyExcel，主要测试自定义convert,编写各个接口导出的字段的通用convert
```agsl
com.example.demo.EasyExcelTest
```

#### liteflow，主要测试自定义业务引擎写法
```agsl
com.example.demo.controller.TestController.testConfig
```

#### guava-retrying 主要测试重试框架中根据每次重试结果的自定义逻辑添加
```agsl
com.example.demo.RetryTest.main
```

#### 测试容器启动时找不到properties文件时，是否会报错，报的什么错
```agsl
src/main/java/com/example/demo/AliPayBean.java
```

#### 测试stopwatch用发
```agsl
com/example/demo/StopWatchTest.java
```

