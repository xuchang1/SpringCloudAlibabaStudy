package com.study.controller;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义json格式的限流阻塞返回结果
 *
 * @author changxu13
 * @date 2021/4/6 19:10
 */
//@Service
public class CustomUrlBlockHandler implements UrlBlockHandler {
	@Override
	public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
		httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
		String message = "{\"code\":999, \"msg\":\"访问人数过多\"}";
		httpServletResponse.getWriter().write(message);
	}
}
