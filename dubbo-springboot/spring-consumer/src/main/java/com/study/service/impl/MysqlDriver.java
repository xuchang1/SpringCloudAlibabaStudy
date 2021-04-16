package com.study.service.impl;

import com.study.service.Driver;

/**
 * @author changxu13
 * @date 2021/4/16 17:44
 */
public class MysqlDriver implements Driver {

	@Override
	public String connect() {
		return "连接mysql数据库!";
	}
}
