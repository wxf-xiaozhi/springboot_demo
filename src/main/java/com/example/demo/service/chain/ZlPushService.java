package com.example.demo.service.chain;

import cn.hutool.json.JSONUtil;
import com.example.demo.dao.ProductReportPushHistoryCrudService;
import com.example.demo.domain.ProductReport;
import com.example.demo.enums.ActivityPatternEnum;
import com.example.demo.enums.ZLSystemBizTypeEnum;
import com.example.demo.service.chain.node.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
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
    APushNode zlQjsPushNode;

    @Autowired
    BPushNode yhUnionProductPushNode;

    @Autowired
    CPushNode yhCashBackRulePushNode;

    @Autowired
    DPushNode dnfActivityPushNode;



    @Autowired
    ProductReportPushHistoryCrudService historyCrudService;

    /**
     * 活动模式和推送链的关系
     */
    Map<Integer, PushZLChain> activityPattenPushChainRule;

    Map<Integer,PushZLChain> pushZLChainMap = new HashMap<>() ;

    /**
     * 构建子业务类型和推送chain关系
     * @return
     */
    @PostConstruct
    public void init(){
        this.buildActivityPattenPushChainRuleRelation();
    }

    /**
     * 根据活动模式和是否是券包，获得所需要进行的执行链
     */
    public void buildActivityPattenPushChainRuleRelation(){

        this.activityPattenPushChainRule = new HashMap<>();
        PushZLChain pushZLChain9 = this.buildPushChain9();




        // 权益券-数币红包活动
        this.activityPattenPushChainRule.put(ActivityPatternEnum.UNION_PRODUCT_DCEP_RED_PACKAGE.getCode(), pushZLChain9);

    }





    private PushZLChain buildPushChain9() {
        PushZLChain pushChain = new PushZLChain(9, this.historyCrudService);
        pushChain.addLast(new DefaultPushNode(this.dnfActivityPushNode));
        pushChain.addLast(new DefaultPushNode(this.yhCashBackRulePushNode));
        pushChain.addLast(new DefaultPushNode(this.yhUnionProductPushNode));
        pushChain.addLast(new DefaultPushNode(this.zlQjsPushNode));
        pushChain.setChainName("FULL");
        this.pushZLChainMap.put(pushChain.getId(),pushChain);
        return pushChain;
    }

    /**
     *
     * 根据活动模式获取执行链的id,存入productReport表的reportType
     * @param activityPattern
     * @return
     */
    public Integer getReportTypeByPatternPackage(Integer activityPattern) {
        PushZLChain pushZLChain = this.activityPattenPushChainRule.get(activityPattern);
        if(pushZLChain!= null){
            return pushZLChain.getId();
        }
        return null;
    }

    /**
     * 开始推送给众联相关业务方
     * @param
     */
    public Boolean startPush(ProductReport report) {
        Integer reportType = report.getReportType();
        PushZLChain pushZLChain = this.pushZLChainMap.get(reportType);
        log.info("============开始向众联推送业务信息,chainId:{},reportId:{}=========================",pushZLChain.show(), JSONUtil.toJsonStr(report));
        return pushZLChain.start(report);
    }

    /**
     * 从bizType节点开始推送众联相关数据
     */
    public Boolean startPushFromZlBizType(ProductReport report, Integer zlBizType){
        Integer reportType = report.getReportType();
        PushZLChain pushZLChain = this.pushZLChainMap.get(reportType);
        log.info("============开始从【{}】向众联推送业务信息，chainId:{},reportId:{}=========================", ZLSystemBizTypeEnum.getByCode(zlBizType).getDesc(),pushZLChain.show(),report);
        return pushZLChain.startByBizType(report,zlBizType);
    }

    /**
     * 单个推送某个业务类型
     */
    public Boolean singleBizPush(ProductReport report,Integer zlBizType){
        PushZLChain pushZLChain = this.pushZLChainMap.get(9);
        log.info("============开始向众联推送单个业务信息，zlBizType:{},reportId:{}=========================", ZLSystemBizTypeEnum.getByCode(zlBizType).getDesc(),report);
        return pushZLChain.singleBizPush(report,zlBizType);
    }

    /**
     * 根据推送链类型，获取业务类型集合
     * @param reportType
     * @return
     */
    public List<Integer> getPushBizTypeList(Integer reportType){
        PushZLChain pushZLChain = this.getPushChainByReportType(reportType);
        return pushZLChain.getBizTypeList();
    }

    public PushZLChain getPushChainByReportType(Integer reportType){
        return this.pushZLChainMap.get(reportType);
    }

    public Map<Integer,PushZLChain> allPushChain(){
        return this.pushZLChainMap;
    }

    public Map<Integer,PushZLChain> activityPattenPushRuleRelation(){
        return this.activityPattenPushChainRule;
    }




}
