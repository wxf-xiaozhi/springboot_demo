package com.example.demo.service.chain;

import com.example.demo.dao.ProductReportPushHistoryCrudService;
import com.example.demo.service.chain.node.PushNode;
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
public class ChainBuild {

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
    Map<Integer, PushZLChainExecute> pushZLChainMap = new HashMap<>() ;



    PushZLChainExecute buildPushChain9() {
        PushZLChainExecute pushChain = new PushZLChainExecute(9, this.historyCrudService);
        pushChain.addLast(new PushNode(this.dnfActivityPushNode));
        pushChain.addLast(new PushNode(this.yhCashBackRulePushNode));
        pushChain.addLast(new PushNode(this.yhUnionProductPushNode));
        pushChain.addLast(new PushNode(this.zlQjsPushNode));
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
        PushZLChainExecute pushZLChainExecute = this.getPushChainByReportType(reportType);
        return pushZLChainExecute.getBizTypeList();
    }

    public PushZLChainExecute getPushChainByReportType(Integer reportType){
        return this.pushZLChainMap.get(reportType);
    }

    public Map<Integer, PushZLChainExecute> allPushChain(){
        return this.pushZLChainMap;
    }


}
