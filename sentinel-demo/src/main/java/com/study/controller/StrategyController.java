package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxu13
 * @date 2022/2/23 9:53
 */
@RestController
public class StrategyController {

	private String value;

	@SentinelResource(value = "read")
	@GetMapping("read")
	public String read() {
		return value;
	}

	@SentinelResource(value = "write")
	@GetMapping("write")
	public void write(String id) {
		value = id;
	}
}
