package com.study.service;

import org.apache.dubbo.config.annotation.Service;

/**
 * @author changxu13
 * @date 2021/4/1 15:14
 */
@Service
public class HelloServiceImpl implements IHelloService{
	@Override
	public String sayHello(String name) {
		return "Hello World : " + name;
	}
}
