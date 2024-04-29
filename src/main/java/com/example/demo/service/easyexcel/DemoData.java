package com.example.demo.service.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
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

        private String string;
        private Date date;
        private Double doubleData;

        @ExcelProperty(converter = CommonIntegerConverter.class)
        private Integer integer;
}
