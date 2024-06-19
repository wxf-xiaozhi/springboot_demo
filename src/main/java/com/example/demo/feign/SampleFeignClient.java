package com.example.demo.feign;

import com.br.logtrace.client.feign.DefaultLogTraceRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * rpc接口
 *
 * @author
 * @version 1.0
 * @date 2022-07-21
 */
@FeignClient(name = "activity-service", url = "localhost:9302",configuration =  {DefaultLogTraceRequestInterceptor.class})
public interface SampleFeignClient {


    @GetMapping("/api/activity/test/sayhello")
    String  sayHello(@RequestParam Integer reportId);




}
