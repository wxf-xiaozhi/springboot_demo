package com.example.demo.service.easyexcel.customconvert;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.example.demo.domain.CasUser;
import com.example.demo.service.CasUserService;
import com.example.demo.utils.ApplicationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: UserConverter
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-10 14:47
 */
public  class CasUserConverter implements Converter<CasUser> {

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }
    @Override
    public CasUser convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String value = StringUtils.trim(cellData.getStringValue());
        Integer rowIndex = cellData.getRowIndex()+1;
        Integer columnIndex = cellData.getColumnIndex()+1;
        if(StringUtils.isNotBlank(value)){
            CasUserService bean = ApplicationUtil.getBean(CasUserService.class);
            List<CasUser> userList = bean.getUserList();
            Map<String, CasUser> casUserMap =
                    userList.stream().collect(Collectors.toMap(CasUser::getUserName, o -> o, (v1, v2) -> v1));
            CasUser casUser = casUserMap.get(value);
            if(ObjectUtil.isNull(casUser)){
                String message = "第%s行第%s列的值:[%s]是无效值";
                String format = String.format(message, rowIndex, columnIndex, value);
                throw new IllegalArgumentException(format);
            }
            return casUser;
        }

        return null;
    }


//    @Override
//    public T convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
//        String data = cellData.getStringValue();
//
//        return Converter.super.convertToJavaData(cellData, contentProperty, globalConfiguration);
//    }
}
