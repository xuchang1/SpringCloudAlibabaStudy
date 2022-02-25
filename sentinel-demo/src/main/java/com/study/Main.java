package com.study;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.study.service.HelloService;
import com.study.service.HelloServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InterruptedException {
	    initFlowRules2();
	    SpringApplication.run(Main.class, args);
//	    doSomething();
//        while (true) {
//	        HelloService helloService = new HelloServiceImpl();
//	        System.out.println(helloService.getUserById("xc"));
//	        Thread.sleep(10);
//        }

//        while (true) {
//            doSomething();
//        }
    }

    private static void doSomething() throws InterruptedException {
        if (SphO.entry("resourceName")) {
            System.out.println("hello world : " + System.currentTimeMillis());
            Thread.sleep(10);
            SphO.exit();
        } else {
            System.out.println("限流了！");
        }

        try {
            Entry entry = SphU.entry("doSomething");
            //业务逻辑处理
            System.out.println("hello world : " + System.currentTimeMillis());
        } catch (BlockException ex) {
            System.out.println("限流了！");
        }
    }

    private static void initFlowRules() {
        List<FlowRule> result = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //需要保护的资源(可以理解为一个共享字符串)
        rule.setResource("hello");
        //限流阈值的类型，1QPS模式，0并发线程数模式
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //限流阈值
        rule.setCount(1);
        result.add(rule);
        FlowRuleManager.loadRules(result);
    }

	private static void initFlowRules2() {
		List<FlowRule> result = new ArrayList<>();

		// 1、写操作限流策略
		FlowRule writeRule = new FlowRule();
		// 写资源
		writeRule.setResource("write");
		//限流阈值的类型，1QPS模式，0并发线程数模式
		writeRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		//限流阈值
		writeRule.setCount(5);
		result.add(writeRule);

		// 2、读操作限流策略
		FlowRule readRule = new FlowRule();
		// 读资源
		readRule.setResource("read");
		//限流阈值的类型，1QPS模式，0并发线程数模式
		readRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		//限流阈值
		readRule.setCount(2);
		readRule.setStrategy(RuleConstant.STRATEGY_RELATE);
		readRule.setRefResource("write");
		result.add(readRule);

		FlowRuleManager.loadRules(result);
	}
}
