package com.example.demo.service.easyexcel.convert;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import org.apache.commons.lang.StringUtils;

public class CommonIntegerConverter implements Converter<Integer> {

        @Override
        public CellDataTypeEnum supportExcelTypeKey() {
            return CellDataTypeEnum.STRING;
        }

        @Override
        public Integer convertToJavaData(ReadConverterContext<?> context) throws Exception {
            String value = StringUtils.trim(context.getReadCellData().getStringValue());
            if (StringUtils.isNotBlank(value)){
                if(!StringUtils.isNumericSpace(value)){
                    context.getAnalysisContext().readRowHolder().getCellMap().get(1).getClass();
                    throw new IllegalArgumentException("单元格的值只能是数字");
                }
                if(!NumberUtil.isInteger(value)){
                    throw new IllegalArgumentException("单元格的值只能是整数");
                }
            }
            return null;


        }

    }