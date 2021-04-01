package com.study.controller;

import com.study.service.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxu13
 * @date 2021/4/1 15:34
 */
@RestController
public class HelloController {

	@Reference
	private IHelloService helloService;

	@GetMapping("say")
	public String sayHello() {
		return helloService.sayHello("Mic");
	}
}
