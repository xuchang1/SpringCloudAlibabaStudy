package com.study.service;

/**
 * @author changxu13
 * @date 2021/4/16 15:20
 */
public class MyDriver implements Driver {
	@Override
	public String connect() {
		return "连接我的数据库!";
	}
}
