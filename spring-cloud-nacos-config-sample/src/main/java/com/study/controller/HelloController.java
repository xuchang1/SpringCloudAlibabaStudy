package com.study.controller;

import com.study.config.MyConfig;
import com.study.config.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author changxu13
 * @date 2021/4/28 11:20
 */
@RestController
public class HelloController {

	@Autowired
	private MyConfig myConfig;

	@Autowired
	private Person person;

	@GetMapping("say")
	public List<String> say() {
		myConfig.list.add("222");
		return myConfig.list;
	}

	@GetMapping("print")
	public List<String> print() {
		return myConfig.list;
	}
	@GetMapping("person")
	public String person() {
		return person.getName();
	}

}
