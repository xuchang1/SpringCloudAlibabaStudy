package com.study.controller;

import com.study.service.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	//@Reference注解获取远程代理对象
	@Reference
	private IHelloService helloService;

	@GetMapping("say")
	public String sayHello() {
		return helloService.sayHello("Mic");
	}
}
