package com.example.demo.service.chain;

import cn.hutool.json.JSONUtil;
import com.example.demo.domain.ProductReport;
import com.example.demo.enums.ActivityPatternEnum;
import com.example.demo.enums.ZLSystemBizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 向众联推送信息的业务类
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/9 19:15
 */
@Component
@Slf4j
public class ZlPushService {



    @Autowired
    ChainBuild chainBuild;





    /**
     * 活动模式和推送链的关系
     */
    Map<Integer, PushZLChainExecute> activityPattenPushChainRule;

    @PostConstruct
    public void init(){
        this.buildActivityPattenPushChainRuleRelation();
    }

    /**
     * 根据活动模式和是否是券包，获得所需要进行的执行链
     */
    public void buildActivityPattenPushChainRuleRelation(){

        this.activityPattenPushChainRule = new HashMap<>();
        PushZLChainExecute pushZLChainExecute9 = this.chainBuild.buildPushChain9();

        // 权益券-数币红包活动
        this.activityPattenPushChainRule.put(ActivityPatternEnum.UNION_PRODUCT_DCEP_RED_PACKAGE.getCode(), pushZLChainExecute9);

    }



    public Map<Integer, PushZLChainExecute> activityPattenPushRuleRelation(){
        return this.activityPattenPushChainRule;
    }


    /**
     * 开始推送给众联相关业务方
     * @param
     */
    public Boolean startPush(ProductReport report) {
        Integer reportType = report.getReportType();
        PushZLChainExecute pushZLChainExecute = this.chainBuild.getPushZLChainMap().get(reportType);
        log.info("============开始向众联推送业务信息,chainId:{},reportId:{}=========================", pushZLChainExecute.show(), JSONUtil.toJsonStr(report));
        return pushZLChainExecute.start(report);
    }

    /**
     * 从bizType节点开始推送众联相关数据
     */
    public Boolean startPushFromZlBizType(ProductReport report, Integer zlBizType){
        Integer reportType = report.getReportType();
        PushZLChainExecute pushZLChainExecute = this.chainBuild.getPushZLChainMap().get(reportType);
        log.info("============开始从【{}】向众联推送业务信息，chainId:{},reportId:{}=========================", ZLSystemBizTypeEnum.getByCode(zlBizType).getDesc(), pushZLChainExecute.show(),report);
        return pushZLChainExecute.startByBizType(report,zlBizType);
    }

    /**
     * 单个推送某个业务类型
     */
    public Boolean singleBizPush(ProductReport report,Integer zlBizType){
        PushZLChainExecute pushZLChainExecute = this.chainBuild.getPushZLChainMap().get(9);
        log.info("============开始向众联推送单个业务信息，zlBizType:{},reportId:{}=========================", ZLSystemBizTypeEnum.getByCode(zlBizType).getDesc(),report);
        return pushZLChainExecute.singleBizPush(report,zlBizType);
    }
    /**
     *
     * 根据活动模式获取执行链的id,存入productReport表的reportType
     * @param activityPattern
     * @return
     */
    public Integer getReportTypeByPatternPackage(Integer activityPattern) {
        PushZLChainExecute pushZLChainExecute = this.activityPattenPushChainRule.get(activityPattern);
        if(pushZLChainExecute!= null){
            return pushZLChainExecute.getId();
        }
        return null;
    }





}
