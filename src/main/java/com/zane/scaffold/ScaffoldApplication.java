package com.zane.scaffold;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.ly.skill.mapper")
public class ScaffoldApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScaffoldApplication.class, args);
	}
}
