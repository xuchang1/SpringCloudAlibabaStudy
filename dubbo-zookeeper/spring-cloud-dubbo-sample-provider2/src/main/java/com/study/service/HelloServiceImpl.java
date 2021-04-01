package com.study.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

//dubbo的@Service注解，将当前服务发布成一个远程服务
@Service
public class HelloServiceImpl implements IHelloService{

    @Value("${spring.application.name}")
    private String serviceName;

    public String sayHello(String name) {
        return String.format("[%s]: Hello, %s", serviceName, name);
    }
}
