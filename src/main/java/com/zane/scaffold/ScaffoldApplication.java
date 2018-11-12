package com.zane.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类(若不配置端口，默认启动自身tomcat8080端口)
 */
@SpringBootApplication
public class ScaffoldApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScaffoldApplication.class, args);
		System.out.println("--------------------------------您好！spring boot已经启动成功---------------------------------");
	}
}