package com.study;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author changxu13
 * @date 2021/4/7 10:58
 */
@DubboComponentScan
@SpringBootApplication
public class ProviderMain {
	public static void main(String[] args) {
		SpringApplication.run(ProviderMain.class, args);
	}
}
