package com.study.controller;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author changxu13
 * @date 2021/4/6 19:40
 */
//@Service
public class CustomerUrlCleaner implements UrlCleaner {
	@Override
	public String clean(String originUrl) {
		if (StringUtils.isEmpty(originUrl)) {
			return originUrl;
		}

		if (originUrl.startsWith("/clean/")) {
			return "/clean/*";
		}
		return originUrl;
	}
}
