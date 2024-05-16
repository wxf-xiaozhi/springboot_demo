package com.example.demo.advice;

import com.example.demo.vo.UserVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 企图通过此方法来控制是输出类型的列，已经放弃，还是基于sql进行
 */
@ControllerAdvice
public class CustomResponseAdvice implements ResponseBodyAdvice<UserVO> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return  returnType.getParameterType() == UserVO.class;
    }

    @Override
    public UserVO beforeBodyWrite(UserVO body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        body.setName("Custom " + body.getName());
        return body;
    }


}