package com.example.demo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.enums.ActivityPatternEnum;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @ClassName: User
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 15:04
 */
@Data
@JsonIgnoreProperties({ "age", "email" })
public class UserVO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @JsonEnumDefaultValue
    private ActivityPatternEnum activityPattern;

}
