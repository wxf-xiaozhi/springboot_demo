package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: CasUser
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-10 14:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasUser implements Serializable {

    private String userName;

    private Integer age;

    private Integer sex;
}
