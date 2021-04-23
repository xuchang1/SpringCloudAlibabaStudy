package com.study.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author changxu13
 * @date 2021/4/23 15:12
 */
public interface OrderSource {
	/**
	 * Name of the output channel.
	 */
	String OUTPUT = "orderOutPut";

	/**
	 * @return output channel
	 */
	@Output(OrderSource.OUTPUT)
	MessageChannel output();
}
