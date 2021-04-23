package com.study.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author changxu13
 * @date 2021/4/23 15:42
 */
public interface OrderSink {
	/**
	 * Input channel name.
	 */
	String INPUT = "orderInput";

	/**
	 * @return input channel.
	 */
	@Input(OrderSink.INPUT)
	SubscribableChannel input();
}
