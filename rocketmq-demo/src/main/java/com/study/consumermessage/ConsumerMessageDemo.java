package com.study.consumermessage;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author changxu13
 * @date 2021/4/20 10:13
 */
public class ConsumerMessageDemo {

	private static final String nameServerAddr = "localhost:9876";

	@Test
	public void consumerMessage() throws MQClientException, InterruptedException {
		// 实例化消费者
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test");
		consumer.setNamesrvAddr(nameServerAddr);

		// 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
		consumer.subscribe("TopicTest", "*");

		//基于sql筛选消息
//		consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));

		// 注册回调实现类来处理从broker拉取回来的消息
		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			msgs.forEach(x ->
					System.out.printf("%s Receive New Messages: %s %n",
							Thread.currentThread().getName(), new String(x.getBody(), StandardCharsets.UTF_8)));
			// 标记该消息已经被成功消费
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});

		// 启动消费者实例
		consumer.start();
		System.out.printf("Consumer Started.%n");
		synchronized (nameServerAddr) {
			nameServerAddr.wait();
		}
	}
}
