package com.example.demo.service.easyexcel.convert;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.read.metadata.holder.AbstractReadHolder;
import com.alibaba.excel.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;

public class CommonDateConverter implements Converter<Date> {

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    // 获取列index
    // 获取行index
    // 获取列name
    @Override
    public Date convertToJavaData(ReadConverterContext<?> context) throws Exception {
        BigDecimal value = context.getReadCellData().getNumberValue();
        String stringValue = context.getReadCellData().getStringValue();
        System.out.println("----------------"+stringValue);
        Integer rowIndex = context.getReadCellData().getRowIndex() ;
        Integer columnIndex = context.getReadCellData().getColumnIndex() ;
        AbstractReadHolder readHolder = (AbstractReadHolder)context.getAnalysisContext().currentReadHolder();
        Integer headRowNumber = readHolder.getHeadRowNumber();
        if (StringUtils.isNotBlank(stringValue)){
            String msgPrefix = String.format("第%s行，第%s列的单元格的值:[%s]",rowIndex+1,columnIndex+1,stringValue);
            throw new IllegalArgumentException(msgPrefix+"不符合日期格式");
        }
        if (ObjectUtil.isNotNull(value)){
            return DateUtils.getJavaDate(value.doubleValue(),false);
        }
        return null;


    }

}