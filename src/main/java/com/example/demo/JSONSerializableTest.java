package com.example.demo;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.enums.ActivityPatternEnum;
import com.example.demo.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: JSONSerializable
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-17 15:46
 */
@Slf4j
public class JSONSerializableTest {

    public static void main(String[] args) {


        UserVO userVo = new UserVO();
        userVo.setActivityPattern(ActivityPatternEnum.UNION_PRODUCT_BANK_COUPON);
        userVo.setName("myname");
        String hutoolsStr = JSONUtil.toJsonStr(userVo);
        log.info("hutools object:{} => jsonstr:{}",userVo,hutoolsStr);

        String fastjsonStr = JSONObject.toJSONString(userVo);

        log.info("fastjson  object:{} => jsonstr:{}", userVo,fastjsonStr);


        String fastjson2Str = com.alibaba.fastjson2.JSONObject.toJSONString(userVo);
        log.info("fastjson2 object:{} => jsonstr:{}", userVo,fastjson2Str);


        ObjectMapper mapper = new ObjectMapper();
        String machineAsJsonString = null;
        try {
            machineAsJsonString = mapper.writeValueAsString(userVo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("jackson object:{} => jsonstr:{}",userVo,machineAsJsonString);



        String str2= "{\"name\":\"myname\",\"activityPattern\":10}";



        UserVO jacksonuserVo = null;
        try {
            jacksonuserVo = mapper.readValue(str2, UserVO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("jackJson str:{} =>{}",str2,jacksonuserVo);

        UserVO hutooluserVo = JSONUtil.toBean(str2, UserVO.class);
        log.info("hutool str:{} =>{}",str2,hutooluserVo);
        UserVO fastuserVo = JSONObject.parseObject(str2, UserVO.class);
        log.info("fastjson str:{} =>{}",str2,fastuserVo);
        UserVO fast2userVo = com.alibaba.fastjson2.JSONObject.parseObject(str2, UserVO.class);
        log.info("fastJSON2 str:{} =>{}",str2,fast2userVo);
    }
}
