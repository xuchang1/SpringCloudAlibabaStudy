package com.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxu13
 * @date 2021/4/6 19:54
 */
@RestController
public class NacosController {

	@GetMapping("nacosDemo")
	public String nacosDemo() {
		return "Hello Nacos";
	}
}
