/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.example.demo.aspect;


import com.example.demo.annotation.LogOperation;
import com.example.demo.domain.SysLogOperationEntity;
import com.example.demo.enums.OperationStatusEnum;
import com.example.demo.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 操作日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
@Slf4j
public class LogOperationAspect {

//    private final SysLogOperationService sysLogOperationService;

    @Pointcut("@annotation(com.example.demo.annotation.LogOperation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            //执行方法
            Object result = point.proceed();

            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, OperationStatusEnum.SUCCESS.value());

            return result;
        } catch (Exception e) {
            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, OperationStatusEnum.FAIL.value());

            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Integer status) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        LogOperation annotation = method.getAnnotation(LogOperation.class);

        SysLogOperationEntity log1 = new SysLogOperationEntity();
        if (annotation != null) {
            //注解上的描述
            log1.setOperation(annotation.value());
        }
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("url:{}耗时：{}",request.getRequestURI(),time);

//        //登录用户信息
//        UserDetail user = SecurityUser.getUser();
//        if (user != null) {
//            log.setCreatorName(user.getUsername());
//        }

//        log.setStatus(status);
//        log.setRequestTime((int) time);

        //请求相关信息
//        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//        log.setIp(IpUtils.getIpAddr(request));
//        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
//        log.setRequestUri(request.getRequestURI());
//        log.setRequestMethod(request.getMethod());
//
//        //请求参数
//        Object[] args = joinPoint.getArgs();
//        try {
//            String params = JsonUtils.toJsonString(args[0]);
//            log.setRequestParams(params);
//        } catch (Exception e) {
//
//        }

        //保存到DB
//        sysLogOperationService.save(log);
    }
}