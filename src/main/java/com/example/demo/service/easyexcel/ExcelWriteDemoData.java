package com.example.demo.service.easyexcel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName: DemoData
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-28 15:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelWriteDemoData {

        @ExcelProperty(index = 0,value = "字符串标题")
        private String string;

        @ExcelProperty(index = 1,value = "日期标题")
        private Date date;

        @ExcelIgnore
        private Double mydouble;

        @ExcelProperty(index = 2,value = "数字标题")
        private Double doubleData;

        @ExcelProperty(index = 3,value = "整数标题")
        private Integer integer;



}

