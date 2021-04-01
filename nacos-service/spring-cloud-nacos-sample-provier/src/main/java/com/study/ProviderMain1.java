package com.study;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author changxu13
 * @date 2021/4/1 15:14
 */
@DubboComponentScan
@SpringBootApplication
public class ProviderMain1 {

	public static void main(String[] args) {
		SpringApplication.run(ProviderMain1.class, args);
	}
}
