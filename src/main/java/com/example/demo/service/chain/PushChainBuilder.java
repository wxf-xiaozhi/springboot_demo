package com.example.demo.service.chain;

import com.example.demo.dao.ProductReportPushHistoryCrudService;
import com.example.demo.service.chain.node.ListNode;
import com.example.demo.service.chain.push.APush;
import com.example.demo.service.chain.push.BPush;
import com.example.demo.service.chain.push.CPush;
import com.example.demo.service.chain.push.DPush;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ChainBuild
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-06-12 00:51
 */
@Component
@Slf4j
public class PushChainBuilder {

    @Autowired
    APush zlQjsPushNode;

    @Autowired
    BPush yhUnionProductPushNode;

    @Autowired
    CPush yhCashBackRulePushNode;

    @Autowired
    DPush dnfActivityPushNode;


    @Autowired
    ProductReportPushHistoryCrudService historyCrudService;



    @Getter
    Map<Integer, PushChain> pushZLChainMap = new HashMap<>() ;



    PushChain buildPushChain9() {
        PushChain pushChain = new PushChain(9, this.historyCrudService);
        pushChain.addLast(new ListNode(this.dnfActivityPushNode));
        pushChain.addLast(new ListNode(this.yhCashBackRulePushNode));
        pushChain.addLast(new ListNode(this.yhUnionProductPushNode));
        pushChain.addLast(new ListNode(this.zlQjsPushNode));
        pushChain.setChainName("FULL");
        this.pushZLChainMap.put(pushChain.getId(),pushChain);
        return pushChain;
    }


    /**
     * 根据推送链类型，获取业务类型集合
     * @param reportType
     * @return
     */
    public List<Integer> getPushBizTypeList(Integer reportType){
        PushChain pushChain = this.getPushChainByReportType(reportType);
        return pushChain.getBizTypeList();
    }

    public PushChain getPushChainByReportType(Integer reportType){
        return this.pushZLChainMap.get(reportType);
    }


}
