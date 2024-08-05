package com.example.demo.service;

import com.example.demo.dao.UserCrudServiceImpl;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName: CasUserService
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-10 14:52
 */
@Service
public class UserService {

    @Resource
    private UserCrudServiceImpl userCrudService;

    @Resource
    CasUserService casUserService;

    //, propagation=  Propagation.SUPPORTS
    @Transactional(rollbackFor = Exception.class)
    public String saveUser(User user) {
        String string = UUID.randomUUID().toString();
        user.setName(string);
        userCrudService.save(user);
        this.saveMyUser(string);
        int i = user.getAge() / user.getAge();
        return "成功";
    }

    public void saveMyUser(String userName){
        User build = User.builder().name(userName+".bak").age(10).build();
        userCrudService.save(build);
    }
}
