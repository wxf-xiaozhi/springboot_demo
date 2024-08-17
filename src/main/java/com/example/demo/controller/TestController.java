package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.UserCrudServiceImpl;
import com.example.demo.domain.User;
import com.example.demo.enums.ActivityPatternEnum;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestController
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-23 17:41
 */
@RestController
public class TestController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private FlowExecutor flowExecutor;

    @Resource
    private UserCrudServiceImpl userCrudService;

    @Resource
    UserService userService;

    @GetMapping("/api/execflow")
    public void testConfig(){
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
    }


    @GetMapping("/userlist")
    public List<UserVO> getUser(){
        List<User> list = userCrudService.list();
        List<UserVO> lists = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(list.get(i),userVO);
            userVO.setActivityPattern(ActivityPatternEnum.PERSON_IMMEDIATELY_MONEY);
            lists.add(userVO);
        }
        return lists;
    }

    @GetMapping("/getUserId")
    public UserVO getUserById(@RequestParam Long id){
        User user =  userCrudService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    /**
     * 测试自调用，事务是否生效，事务传播机制对事务回滚的影响
     * @param user
     * @return
     */
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user){
       String a =  userService.saveUser(user);
       return a;
    }

    @PostMapping("/testMulget")
    public List<String> materialUpload(@RequestBody JSONObject param) throws Exception {
        JSONArray list = param.getJSONArray("list");
        List<String> a= new ArrayList<>();
        for (Object o : list) {
            a.add(o.toString());
        }

        return multiGet(a);
    }


    public List<String> multiGet(List<String> keys) {
        return this.stringRedisTemplate.opsForValue().multiGet(keys);
    }
}
