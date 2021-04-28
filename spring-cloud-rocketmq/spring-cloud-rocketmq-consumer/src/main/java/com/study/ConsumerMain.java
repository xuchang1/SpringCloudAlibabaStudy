package com.study;

import com.study.service.OrderSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author changxu13
 * @date 2021/4/23 14:44
 */
@EnableBinding({Sink.class, OrderSink.class})
@SpringBootApplication
public class ConsumerMain {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMain.class, args);
	}

	@StreamListener(value = Sink.INPUT)
	public void receive(String receiveMsg) {
		System.out.println("receive msg : " + receiveMsg);
	}

	@StreamListener(value = OrderSink.INPUT)
	public void receive2(String receiveMsg) {
		System.out.println("receive msg2 : " + receiveMsg);
	}

}
