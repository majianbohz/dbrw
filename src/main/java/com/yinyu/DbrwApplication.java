package com.yinyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.yinyu.mapper")
@ServletComponentScan
public class DbrwApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbrwApplication.class, args);
	}

}
