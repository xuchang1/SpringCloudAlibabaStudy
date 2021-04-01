package com.study.service;

/**
 * @author changxu13
 * @date 2021/4/1 11:14
 */
public class MockHelloService implements IHelloService{
	public String sayHello(String name) {
		return "Sorry, 服务无法访问，返回降级数据";
	}
}
