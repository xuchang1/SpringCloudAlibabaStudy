package com.study.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author changxu13
 * @date 2021/4/6 13:50
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String getUserById(String id) {
		return id + "111";
	}
}
