package com.study.controller;

import com.study.service.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    //@Reference注解获得dubbo的一个远程代理对象
    @Reference(url = "dubbo://192.168.0.107:20880/com.study.service.IHelloService")
    private IHelloService helloService;

    @GetMapping("hello")
    public String sayHello() {
        return helloService.sayHello("Mic");
    }
}
