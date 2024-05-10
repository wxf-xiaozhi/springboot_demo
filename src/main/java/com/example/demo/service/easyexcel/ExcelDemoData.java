package com.example.demo.service.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.demo.domain.CasUser;
import com.example.demo.service.easyexcel.convert.CommonBooleanConverter;
import com.example.demo.service.easyexcel.convert.CommonConverter;
import com.example.demo.service.easyexcel.convert.CommonDateConverter;
import com.example.demo.service.easyexcel.customconvert.CasUserConverter;
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
public class ExcelDemoData {

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


        @ExcelProperty(index = 6,converter = CommonConverter.class)
        private CasUser user;

}

