package com.example.demo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.ProductRecordPushHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 提报推送历史表
 *
 * @author haiqiang.xu
 * @date 2024/3/14 20:18
 */
@Mapper
public interface ProductReportPushHistoryMapper extends BaseMapper<ProductRecordPushHistory> {
}
