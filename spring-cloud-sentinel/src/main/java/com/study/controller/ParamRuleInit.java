package com.study.controller;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

/**
 * @author changxu13
 * @date 2021/4/7 17:18
 */
@Component
public class ParamRuleInit {

	@PostConstruct
	public void intiParamRule() {
		//热点参数限流
		ParamFlowRule paramFlowRule = new ParamFlowRule("hello1");
		paramFlowRule.setCount(1);
		paramFlowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		paramFlowRule.setParamIdx(0);
		ParamFlowRuleManager.loadRules(Collections.singletonList(paramFlowRule));
	}
}
