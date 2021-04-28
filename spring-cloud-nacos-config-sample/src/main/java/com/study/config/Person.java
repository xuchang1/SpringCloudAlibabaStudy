package com.study.config;

import org.springframework.stereotype.Component;

/**
 * @author changxu13
 * @date 2021/4/28 11:49
 */
public class Person {

	public Person(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
