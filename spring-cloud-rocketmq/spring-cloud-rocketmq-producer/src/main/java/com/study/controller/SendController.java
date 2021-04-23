package com.study.controller;

import com.study.service.OrderSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxu13
 * @date 2021/4/23 14:57
 */
@RestController
public class SendController {

	@Autowired
	private Source source;

	@Autowired
	private OrderSource orderSource;

	@GetMapping("send")
	public String send(String msg) {
		MessageBuilder<String> messageBuilder = MessageBuilder.withPayload(msg);
		Message<String> message = messageBuilder.build();
		source.output().send(message);
		orderSource.output().send(message);
		return "Hello World!";
	}
}
