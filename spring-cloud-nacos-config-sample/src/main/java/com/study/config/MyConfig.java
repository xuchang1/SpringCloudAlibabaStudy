package com.study.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changxu13
 * @date 2021/4/28 11:15
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "a")
public class MyConfig implements ApplicationContextAware {
	public List<String> list = new ArrayList<>();

	private ApplicationContext applicationContext;

	//nacos更新配置数据后，RefreshScope使得当前bean被调用时重试初始化
	@Bean
	@RefreshScope
	public Person person() {
		return new Person(applicationContext.getEnvironment().getProperty("info"));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
