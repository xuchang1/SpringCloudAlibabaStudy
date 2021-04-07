package com.study.config;

import org.apache.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changxu13
 * @date 2021/4/7 15:45
 */
@Configuration
public class AppConfig {

	//启动失败，找不到过滤器
//	@Bean
	public ConsumerConfig consumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setFilter("-sentinel.dubbo.filter");
		return consumerConfig;
	}
}
