package com.study.service;

import org.apache.dubbo.config.annotation.Service;

/**
 * @author changxu13
 * @date 2021/4/7 10:58
 */
@Service
public class HelloServiceImpl implements IHelloService{
	@Override
	public String say(String id) {
		return id + "1";
	}
}
