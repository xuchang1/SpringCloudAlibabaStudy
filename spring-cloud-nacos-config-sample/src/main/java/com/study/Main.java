package com.study;

import com.study.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author changxu13
 * @date 2021/4/1 16:06
 */
@SpringBootApplication
public class Main {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
//		while (true) {
//			Thread.sleep(1000);
//			String info = context.getEnvironment().getProperty("info");
//			System.out.println(info);
//		}

	}
}
