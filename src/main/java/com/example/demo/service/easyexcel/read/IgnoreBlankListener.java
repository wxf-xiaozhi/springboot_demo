package com.example.demo.service.easyexcel.read;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @title: 忽略空行Listener
 */
@Slf4j
public class IgnoreBlankListener<T> extends PageReadListener<T> {
    private List<T> cache = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private final Consumer<List<T>> consumer;
    public IgnoreBlankListener(Consumer<List<T>> consumer) {
        super(consumer);
        this.consumer = consumer;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        // 如果一行Excel数据均为空值，则不装载该行数据
        if (this.lineNull(data)) {
            return;
        }
        this.cache.add(data);
        if (this.cache.size() >= BATCH_COUNT) {
            this.consumer.accept(this.cache);
            this.cache = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (this.cache == null || this.cache.isEmpty()) {
            return;
        }
        this.consumer.accept(this.cache);
    }
    boolean lineNull(T line) {
        if (line instanceof String) {
            return StringUtils.isEmpty((String) line);
        }
        Set<Field> fields = Arrays.stream(line.getClass().getDeclaredFields()).filter(f -> f.isAnnotationPresent(ExcelProperty.class)).collect(Collectors.toSet());
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(line) != null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                log.error("lineNull异常",e);
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
