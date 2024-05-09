package com.example.demo.service.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.service.easyexcel.convert.CommonBooleanConverter;
import com.example.demo.service.easyexcel.convert.CommonDateConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.example.demo.service.easyexcel.convert.CommonIntegerConverter;

import java.util.Date;

/**
 * @ClassName: DemoData
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-28 15:45
 */
@Data
public class DemoData {

        @ExcelProperty(index = 0)
        private String string;

        @ExcelProperty(index = 1)
        private Date date;

        @ExcelProperty(index = 2)
//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Double doubleData;

        @ExcelProperty(index = 3,converter = CommonIntegerConverter.class)
        private Integer integer;

        @ExcelProperty(index = 4,converter = CommonBooleanConverter.class)
        private Boolean active;

        @ExcelProperty(index = 5,converter = CommonDateConverter.class)
//        @DateTimeFormat
//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//        @JSONField(format =   "yyyy-MM-dd HH:mm:ss")
//        @DateTimeFormat( value= "yyyy-MM-dd HH:mm:ss")
        private Date dateData;

}

