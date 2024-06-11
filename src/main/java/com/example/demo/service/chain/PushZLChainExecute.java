package com.example.demo.service.chain;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.demo.dao.ProductReportPushHistoryCrudService;
import com.example.demo.domain.ProductRecordPushHistory;
import com.example.demo.domain.ProductReport;
import com.example.demo.enums.ZLSystemBizTypeEnum;
import com.example.demo.service.chain.commonresult.ZlPushCommResult;
import com.example.demo.service.chain.node.PushNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 推送给众联的推送链
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/9 17:59
 */
@Data
@Slf4j
public class PushZLChainExecute {
    /**
     * 执行链名称
     */
    String chainName;

    /**
     * 执行链id
     */
    Integer id;

    /**
     * 执行链包含的众联推送业务类型集合
     */
    List<Integer> bizTypeList = new ArrayList<>();


    ProductReportPushHistoryCrudService historyCrudService;

    public PushZLChainExecute(Integer id, ProductReportPushHistoryCrudService historyCrudService) {
        this.id = id;
        this.historyCrudService = historyCrudService;
    }

    /**
     * 执行链包含的众联推送业务类型集合
     */
    List<ZLSystemBizTypeEnum> bizEnumTypeList = new ArrayList<>();

    PushNode head = null;

    PushNode tail = null;

    public void addLast(PushNode... nodes) {
        for (PushNode node : nodes) {
            this.bizTypeList.add(node.getValue().getZlBizType());
            this.bizEnumTypeList.add(ZLSystemBizTypeEnum.getByCode(node.getValue().getZlBizType()));
            if(this.head == null){
                this.head = node;
                this.tail = this.head;
            }else{
                this.tail.setNext(node);
                this.tail = this.tail.getNext();
            }
        }
    }

    /**
     * 开始执行推送链
     * @param report
     */
    public Boolean start(ProductReport report)  {
        return this.execChain(report,null);
    }

    /**
     * 从某个点开始推送，用于chain上某个node推送失败后的，在chain的从某个节点开始重新推送
     * @param report
     * @param zlBizType
     */
    public Boolean startByBizType(ProductReport report, Integer zlBizType)  {
        return this.execChain(report,zlBizType);
    }

    /**
     * 单个业务类型推送
     * @param report
     * @param zlBizType @see com.br.zlop.activity.enums.ZLSystemBizTypeEnum
     */
    public Boolean singleBizPush(ProductReport report, Integer zlBizType)  {
        return this.execSinglePush(report,zlBizType);
    }

    /**
     * 推送单个业务类型的方法
     * @param report
     * @param zlBizType
     * @return
     */
    public Boolean execSinglePush(ProductReport report, Integer zlBizType){
        Boolean pushResult = false;
        PushNode node = this.head;
        while (node != null){
            if(zlBizType != null && zlBizType.equals(node.getValue().getZlBizType())){
                ZlPushCommResult zlPushCommResult = node.getValue().pushZl(report);
                ProductRecordPushHistory productRecordPushHistory =ProductRecordPushHistory.builder().chainId(this.id).reportId(report.getId())
                        .bizTypes(StringUtils.join(this.bizTypeList,",")).bizTypeNames(StringUtils.join(this.bizEnumTypeList,","))
                        .currentType(node.getValue().getZlBizType()).currentTypeName(node.getValue().getZlBizName()).traceId(MDC.get("traceId")).build();
                if(ObjectUtils.isNotEmpty(zlPushCommResult)){
                    pushResult = node.getValue().checkSuccess(zlPushCommResult);
                    if(node.getValue().isNeedSuccessCallBack(zlPushCommResult)){
                        node.getValue().successCallBack(zlPushCommResult,report);
                    }
                }
                productRecordPushHistory.setCurrentPushResult(pushResult?0:1);
                this.historyCrudService.save(productRecordPushHistory);
                break;
            }
            node = node.getNext();
        }
        return pushResult;
    }

    public Boolean execChain(ProductReport report, Integer zlBizType){
        Integer index = getIndexByOfPushChainBizType(zlBizType);
        PushNode node = this.head;
        int count = 0;
        int continueCount = 0;
        while (node != null){
            // 是否需要继续下一节点
            Boolean isNeedNext = true;
            // 如果当前节点业务类型小于目标业务类型，则跳过，只执行从目标节点开始以后的推送
            if(zlBizType != null && (getIndexByOfPushChainBizType(node.getValue().getZlBizType())) < index){
                continueCount++;
                node = node.getNext();
                continue;
            }
            Boolean pushResult = false;
            log.info("reportId:{},chainId:{},开始推送:{}节点",report.getId(),this.id,node.getValue().getZlBizName());
            ZlPushCommResult zlPushCommResult = node.getValue().pushZl(report);
            ProductRecordPushHistory productRecordPushHistory =ProductRecordPushHistory.builder().chainId(this.id).reportId(report.getId())
                    .bizTypes(StringUtils.join(this.bizTypeList,",")).bizTypeNames(StringUtils.join(this.bizEnumTypeList,","))
                    .currentType(node.getValue().getZlBizType()).currentTypeName(node.getValue().getZlBizName()).traceId(MDC.get("traceId")).build();
            if(ObjectUtils.isNotEmpty(zlPushCommResult)){
                pushResult = node.getValue().checkSuccess(zlPushCommResult);
                if(pushResult){
                    // 如果当前节点需要执行回调，则直接回调
                    if(node.getValue().isNeedSuccessCallBack(zlPushCommResult)){
                        node.getValue().successCallBack(zlPushCommResult,report);
                        log.info("reportId:{},chainId:{},恭喜推送:{}节点成功",report.getId(),this.id,node.getValue().getZlBizName());
                    }
                }else{
                    log.warn("【{}】推送失败，响应：{}",node.getValue().getZlBizName()+"---", JSONUtil.toJsonStr(zlPushCommResult));
                    pushResult = node.getValue().failCallBack(report);
                    // 如果推送还是失败则不在继续往下执行推送链
                    if(!pushResult){
                        isNeedNext = false;
                    }
                }
            }else{
                log.warn("未收到【{}】响应，触发失败逻辑",node.getValue().getZlBizName());
                pushResult = node.getValue().failCallBack(report);
            }
            productRecordPushHistory.setCurrentPushResult(pushResult?0:1);
            this.historyCrudService.save(productRecordPushHistory);
            if(isNeedNext){
                count++;
                node = node.getNext();
            }else{
                log.error("【{}】重试推送失败,推送链路停止,请及时处理",node.getValue().getZlBizName());
                break;
            }
        }
        if(ObjectUtils.isEmpty(zlBizType)){
            if(count == this.bizEnumTypeList.size()){
                List<String> bizNames = this.bizEnumTypeList.stream().map(ZLSystemBizTypeEnum::getDesc).collect(Collectors.toList());
                log.info("-------------------恭喜你，reportId:{},所有节点:[{}],全部成功，尽情享受吧！-----------",report.getId(),bizNames);
                return true;
            }
        }else{
            if(count == this.bizEnumTypeList.size() - continueCount){
                List<String> bizNames = this.bizEnumTypeList.stream().map(ZLSystemBizTypeEnum::getDesc).collect(Collectors.toList());
                log.info("-------------------恭喜你，reportId:{},从【{}】开始，以后所有节点:[{}],全部成功，尽情享受吧！-----------",report.getId(),ZLSystemBizTypeEnum.getByCode(zlBizType).getDesc(),bizNames);
                return true;
            }
        }

        return false;
    }

    /**
     * 根据bizType获取在执行链中的index
     * @param bizType
     * @return
     */
    public Integer getIndexByOfPushChainBizType(Integer bizType){
        for (int i = 0; i < this.bizEnumTypeList.size(); i++) {
            if(this.bizEnumTypeList.get(i).getCode().equals(bizType)){
                return i;
            }
        }
        return null;
    }


    public String show(){
        StringBuilder sb =new StringBuilder("PushZLChain:id="+ this.getId()+"包含");
        PushNode node = this.head;
        while (node != null){
            sb.append(node.getValue().getZlBizName()+"->");
            node = node.getNext();
        }
        log.info(sb.toString());
        return sb.toString();
    }


}
