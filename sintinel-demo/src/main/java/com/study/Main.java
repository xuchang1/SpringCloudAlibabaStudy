package com.study;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        initFlowRules();
        while (true) {
            doSomething();
        }
    }

    private static void doSomething() throws InterruptedException {
        if (SphO.entry("resourceName")) {
            System.out.println("hello world : " + System.currentTimeMillis());
            Thread.sleep(10);
            SphO.exit();
        } else {
            System.out.println("限流了！");
        }

//        try {
//            Entry entry = SphU.entry("doSomething");
//
//            //业务逻辑处理
//            System.out.println("hello world : " + System.currentTimeMillis());
//        } catch (BlockException ex) {
//            System.out.println("限流了！");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void initFlowRules() {
        List<FlowRule> result = new ArrayList<FlowRule>();
        FlowRule rule = new FlowRule();
        rule.setResource("resourceName");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);
        result.add(rule);
        FlowRuleManager.loadRules(result);
    }
}
