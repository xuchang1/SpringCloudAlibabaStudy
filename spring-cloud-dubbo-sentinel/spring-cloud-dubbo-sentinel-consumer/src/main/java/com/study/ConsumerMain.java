package com.study;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author changxu13
 * @date 2021/4/7 15:08
 */
@DubboComponentScan
@SpringBootApplication
public class ConsumerMain {
	public static void main(String[] args) {
		SpringApplication.run(ConsumerMain.class, args);
	}
}
