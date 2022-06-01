package com.study;

import com.study.service.Driver;

import java.util.ServiceLoader;

/**
 * @author changxu13
 * @date 2021/4/16 15:16
 */
public class SourceMain {
	public static void main(String[] args) {
		ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
		for (Driver driver : serviceLoader) {
			System.out.println(driver.connect());
		}
		System.out.println("111111111111111");
	}
}
