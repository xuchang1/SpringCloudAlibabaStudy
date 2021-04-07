package com.study.controller;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author changxu13
 * @date 2021/4/6 17:34
 */
public class FlowRuleInitFunc implements InitFunc {
	@Override
	public void init() throws Exception {
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule = new FlowRule();
		//需要保护的资源(可以理解为一个共享字符串)
		rule.setResource("hello");
		//限流阈值的类型，1QPS模式，0并发线程数模式
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setLimitApp("default");
		//限流阈值
		rule.setCount(1);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}
}
