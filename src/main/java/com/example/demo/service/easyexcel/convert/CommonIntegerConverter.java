package com.example.demo.service.easyexcel.convert;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.read.metadata.holder.AbstractReadHolder;
import com.alibaba.excel.read.metadata.holder.ReadHolder;
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

                    AbstractReadHolder readHolder = (AbstractReadHolder)context.getAnalysisContext().currentReadHolder();
                    Integer headRowNumber = readHolder.getHeadRowNumber();
                    Integer rowIndex = context.getReadCellData().getRowIndex()  ;
                    Integer columnIndex = context.getReadCellData().getColumnIndex() ;
                    String msgPrefix = String.format("第[%s]行，第[%s]列的 ",rowIndex+1,  columnIndex+1);
                    throw new IllegalArgumentException(msgPrefix+"单元格的值只能是整数");
                }
                if(!NumberUtil.isInteger(value)){
                    throw new IllegalArgumentException("单元格的值只能是整数");
                }
            }
            return null;


        }

    }