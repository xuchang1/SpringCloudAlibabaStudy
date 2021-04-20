package com.study.sendmessage;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import sun.security.krb5.internal.crypto.Aes128;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author changxu13
 * @date 2021/4/20 9:32
 */
public class SendMessageDemo {

	private static final String nameServerAddr = "localhost:9876";

	//同步发送消息
	@Test
	public void sendSync() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
		// 实例化消息生产者Producer
		DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
		// 设置NameServer的地址
		producer.setNamesrvAddr(nameServerAddr);
		// 启动Producer实例
		producer.start();
		for (int i = 0; i < 100; i++) {
			// 创建消息，并指定Topic，Tag和消息体
			Message message = new Message("TopicTest",
					"TagA", ("Hello World" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			message.setDelayTimeLevel(2);//延迟消息级别
			// 发送消息到一个Broker
			SendResult sendResult = producer.send(message);
			System.out.printf("%s%n", sendResult);
		}

		// 如果不再发送消息，关闭Producer实例。
		producer.shutdown();
	}

	//异步发送消息
	@Test
	public void sendASync() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
		// 实例化消息生产者Producer
		DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
		// 设置NameServer的地址
		producer.setNamesrvAddr(nameServerAddr);
		// 启动Producer实例
		producer.start();
		//发送异步消息的重试次数
		producer.setRetryTimesWhenSendAsyncFailed(0);

		// 根据消息数量实例化倒计时计算器
		final CountDownLatch2 countDownLatch = new CountDownLatch2(100);

		for (int i = 0; i < 100; i++) {
			final int index = i;
			// 创建消息，并指定Topic，Tag和消息体
			Message message = new Message("TopicTest",
					"TagA", "OrderID188", ("Hello World" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			message.putUserProperty("a", String.valueOf(i));//设置属性a的值，可以基于该属性通过sql进行消息过滤
			// 发送异步消息到一个Broker
			producer.send(message, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
					countDownLatch.countDown();
				}

				@Override
				public void onException(Throwable e) {
					System.out.printf("%-10d Exception %s %n", index, e);
					countDownLatch.countDown();
					e.printStackTrace();
				}
			});

		}

		// 等待5s
		countDownLatch.await(5, TimeUnit.SECONDS);
		// 如果不再发送消息，关闭Producer实例。
		producer.shutdown();
	}

	//单向消息发送
	@Test
	public void sendOneWay() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
		DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
		producer.setNamesrvAddr(nameServerAddr);
		producer.start();
		for (int i = 0; i < 100; i++) {
			Message message = new Message(
					"TopicTest", "TagA", ("Hello World" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			producer.sendOneway(message);
		}
		Thread.sleep(2000);
		producer.shutdown();
	}
}
