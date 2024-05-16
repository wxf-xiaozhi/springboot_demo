package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: UserMapper
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 15:04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
