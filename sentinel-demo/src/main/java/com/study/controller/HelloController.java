package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author changxu13
 * @date 2021/4/6 14:27
 */
@RestController
public class HelloController {

	@Resource
	private HelloService helloService;


	//TODO 直接使用SentinelResource注解，为啥未生效
	@GetMapping("getUserById")
	@SentinelResource(value = "HelloWorld", blockHandler = "blockHandlerForUser")
	public String getUserById(String id) {
		System.out.println(111);
		return id;
	}

	public String blockHandlerForUser(String id, BlockException e) {
		System.out.println("22222222222222");
		return "2222";
	}
}
