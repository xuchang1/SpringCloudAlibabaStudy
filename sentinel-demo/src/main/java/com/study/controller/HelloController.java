package com.study.controller;

import com.alibaba.csp.sentinel.SphO;
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
	@SentinelResource
	public String getUserById(String id) {
		System.out.println(111);
		return id;
	}

	@GetMapping("say")
	@SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
	public String say(long s) {
		return String.format("Hello at %d", s);
	}

	// Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
	public String helloFallback(long s) {
		return String.format("Halooooo %d", s);
	}


	// Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
	public String exceptionHandler(long s, BlockException ex) {
		// Do some log here.
		ex.printStackTrace();
		return "Oops, error occurred at " + s;
	}

	public String blockHandlerForUser(String id, BlockException e) {
		System.out.println("22222222222222");
		return "2222";
	}
}
