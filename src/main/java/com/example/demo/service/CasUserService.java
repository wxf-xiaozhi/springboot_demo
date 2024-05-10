package com.example.demo.service;

import com.example.demo.domain.CasUser;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: CasUserService
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-10 14:52
 */
@Service
public class CasUserService {

    CasUser[] users = new CasUser[10];
    List<CasUser> list = new ArrayList<>();

    @PostConstruct
    public void init(){
        CasUser build1 = CasUser.builder().userName("xiaofang.wu1").age(11).sex(1).build();
        CasUser build2 = CasUser.builder().userName("xiaofang.wu2").age(12).sex(1).build();
        CasUser build3 = CasUser.builder().userName("xiaofang.wu3").age(13).sex(1).build();
        CasUser build4 = CasUser.builder().userName("xiaofang.wu4").age(14).sex(1).build();
        list.add(build1);
        list.add(build2);
        list.add(build3);
        list.add(build4);
    }

    public  List<CasUser> getUserList(){
        return list;
    }
}
