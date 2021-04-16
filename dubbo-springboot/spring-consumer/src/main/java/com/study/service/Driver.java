package com.study.service;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author changxu13
 * @date 2021/4/16 17:44
 */
@SPI
public interface Driver {
	String connect();
}
