package com.study.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 集成Sentinel进行限流
 */
@Configuration
public class GatewayConfiguration {
	private final List<ViewResolver> viewResolvers;

	private final ServerCodecConfigurer serverCodecConfigurer;

	public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
		this.viewResolvers = viewResolvers.getIfAvailable(Collections::emptyList);
		this.serverCodecConfigurer = serverCodecConfigurer;
	}

	//注入SentinelGatewayFilter
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public GlobalFilter sentinelGatewayFilter() {
		return new SentinelGatewayFilter();
	}

	//注入限流异常处理器
//	@Bean
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
//		return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
//	}

	//自定义限流异常
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public GpSentinelGatewayBlockExceptionHandler gpSentinelGatewayBlockExceptionHandler() {
		return new GpSentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
	}

	@PostConstruct
	public void init() {
		initCustomizedApis();
		initGatewayRules();
	}

	//自定义api分组限流
	private void initCustomizedApis() {
		Set<ApiDefinition> apiDefinitions = new HashSet<>();
		//first_customozed_api 限流的资源id
		ApiDefinition apiDefinition = new ApiDefinition("first_customized_api");
		apiDefinition.setPredicateItems(new HashSet<ApiPredicateItem>(){{
			add(new ApiPathPredicateItem().setPattern("/foo/say"));//URL_MATCH_STRATEGY_EXACT,默认策略为精确url匹配，此时foo请求不生效
			add(new ApiPathPredicateItem().setPattern("/baz/**")
					.setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
		}});
		apiDefinitions.add(apiDefinition);
		GatewayApiDefinitionManager.loadApiDefinitions(apiDefinitions);
	}

	//初始化限流规则
	private void initGatewayRules() {
		Set<GatewayFlowRule> rules = new HashSet<>();
		GatewayFlowRule rule = new GatewayFlowRule("nacos-gateway-provider").setCount(2).setIntervalSec(1);
		rules.add(rule);

		//分组名称作为资源id
		//resourceMode : 标识限流维护，route维度、自定义API维度，默认前者
		GatewayFlowRule customerFlowRule = new GatewayFlowRule("first_customized_api")
				.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
				.setCount(1).setIntervalSec(1);
		rules.add(customerFlowRule);

		GatewayRuleManager.loadRules(rules);
	}
}
