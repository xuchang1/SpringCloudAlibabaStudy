package com.study;

import com.study.service.Driver;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
	    ExtensionLoader<Driver> extensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);
	    Driver mysqlDriver = extensionLoader.getExtension("mysqlDriver");
	    System.out.println(mysqlDriver.connect());

	    SpringApplication.run(Main.class, args);
    }
}
