package com.study;

import com.study.service.OrderSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author changxu13
 * @date 2021/4/23 14:44
 */
@EnableBinding({Source.class, OrderSource.class})
@SpringBootApplication
public class ProducerMain {

	public static void main(String[] args) {
		SpringApplication.run(ProducerMain.class, args);
	}
}
