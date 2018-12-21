package com.zerodg.vwdao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//@PropertySource(value = "classpath:application.properties")
//@MapperScan("com.zerodg.zd-vw.vw-dao.mapper")
public class VwDaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VwDaoApplication.class, args);
	}

}

