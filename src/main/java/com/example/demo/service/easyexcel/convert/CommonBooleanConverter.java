package com.example.demo.service.easyexcel.convert;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import org.apache.commons.lang.StringUtils;

public class CommonBooleanConverter implements Converter<Boolean> {

        @Override
        public CellDataTypeEnum supportExcelTypeKey() {
            return CellDataTypeEnum.STRING;
        }

        @Override
        public Boolean convertToJavaData(ReadConverterContext<?> context) throws Exception {
            String value = StringUtils.trim(context.getReadCellData().getStringValue());
                if(ObjectUtil.isNotNull(value)){
                if ("是".equals(value)) {
                    return Boolean.TRUE;
                } else if ("否".equals(value)) {
                    return Boolean.FALSE;
                }else{
                    Integer rowIndex = context.getReadCellData().getRowIndex() ;
                    Integer columnIndex = context.getReadCellData().getColumnIndex() ;
                    String msgPrefix = String.format("第%s行，第%s列的 ",rowIndex+1,columnIndex+1);
                    throw new IllegalArgumentException(msgPrefix+"单元格的值需限：'是', '否'");
                }
            }
            return null;
        }

    }