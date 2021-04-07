package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author changxu13
 * @date 2021/4/6 17:19
 */
@RestController
public class HelloController {

	@SentinelResource(value = "hello", blockHandler = "blockHandlerHello")
	@GetMapping("say")
	public String hello() {
		return "Hello Mic";
	}

	//接口访问后，才在sentinel-dashboard管理平台上看到该接口
	@GetMapping("dash")
	public String dash() {
		return "Hello dash";
	}

	@GetMapping("/clean/{id}")
	public String clean(@PathVariable("id") int id) {
		return "Hello clean" + id;
	}

	@SentinelResource(value = "hello1")
	@GetMapping("/hello1")
	public String sayHello(@PathParam("id") String id) {
		return id;
	}

	public String blockHandlerHello(BlockException e) {
		return "被限流了";
	}
}
