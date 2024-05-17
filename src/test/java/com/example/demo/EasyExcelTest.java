package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.easyexcel.ExcelReadDemoData;
import com.example.demo.service.easyexcel.ExcelWriteDemoData;
import com.example.demo.service.easyexcel.read.DemoDataListener;
import com.example.demo.service.easyexcel.read.IgnoreBlankListener;
import com.example.demo.utils.TestFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: EasyExcelTest
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-28 16:11
 */
@SpringBootTest
@Slf4j
public class EasyExcelTest {



    @Test
    public void getEasyExcel1() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName ="D:\\study_code\\demo1\\src\\main\\resources\\testfile\\demo\\demo.xlsx";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, ExcelReadDemoData.class, new PageReadListener<ExcelReadDemoData>(dataList -> {
            for (ExcelReadDemoData excelReadDemoData : dataList) {
                log.info("读取到一条数据{}", JSONObject.toJSONString(excelReadDemoData));
            }
        })).sheet().doRead();






    }
    @Test
    public void getEasyExcel2() {
        // 写法2：
        // 匿名内部类 不用额外写一个DemoDataListener
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelReadDemoData.class, new ReadListener<ExcelReadDemoData>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 100;
            /**
             *临时存储
             */
            private List<ExcelReadDemoData> cachedDataList = new ArrayList<>(BATCH_COUNT);

            @Override
            public void invoke(ExcelReadDemoData data, AnalysisContext context) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = new ArrayList<>(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                log.info("{}条数据，开始存储数据库！", cachedDataList.size());
                log.info("存储数据库成功！");
            }
        }).sheet().doRead();
    }

    @Test
    public void getEasyExcel3() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法3：
        String fileName ="D:\\study_code\\springboot_demo\\src\\main\\resources\\testfile\\demo\\demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelReadDemoData.class, new DemoDataListener()).sheet().headRowNumber(3).doRead();
    }
    @Test
    public void getEasyExcel5() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法3：
        String fileName ="D:\\study_code\\springboot_demo\\src\\main\\resources\\testfile\\demo\\demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        List<T> reportList = new ArrayList<>();
//        EasyExcel.read(fileName, ExcelDemoData.class, new IgnoreBlankListener<T>(dataList::addAll))
//                .sheet().headRowNumber(3).doRead();
        try {
            EasyExcel.read(fileName, new IgnoreBlankListener<T>(reportList::addAll))
//                    .head(ExcelDemoData.class)
                    .sheet().headRowNumber(3).autoTrim(Boolean.TRUE).doReadSync();
        } catch (ExcelDataConvertException e) {
            log.error("批量导入活动配置出现错误,row:{},col:{}", e.getRowIndex(), e.getColumnIndex(), e);
            throw new IllegalArgumentException("批量导入活动配置出现错误");
        }

    }


    @Test
    public void getEasyExcel4() {
        /**
         *  写法4
         */
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 一个文件一个reader
        try (ExcelReader excelReader = EasyExcel.read(fileName, ExcelReadDemoData.class, new DemoDataListener()).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }
    }

    @Test
    public void indexWrite() {
        String fileName = TestFileUtil.getPath() + "indexWrite" + System.currentTimeMillis() + ".xlsx";

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        int[] a = {1,2};
        List<Integer> array = Arrays.stream(a).boxed().collect(Collectors.toList());
        EasyExcel.write(fileName, ExcelWriteDemoData.class).includeColumnIndexes(array).sheet("模板").doWrite(data());
        log.info(fileName);
    }

    private List<ExcelWriteDemoData> data(){
        List<ExcelWriteDemoData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelWriteDemoData build = ExcelWriteDemoData.builder().doubleData(23d).mydouble(24d).date(new Date()).string("测试" + i).integer(i).build();
            data.add(build);
        }
        return data;
    }
}
