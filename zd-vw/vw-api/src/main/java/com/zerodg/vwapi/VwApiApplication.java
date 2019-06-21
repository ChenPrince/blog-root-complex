package com.zerodg.vwapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
@Configuration
//bean包扫描
@ComponentScan(basePackages = {"com.zerodg.zdutil.util", "com.zerodg.serviceImpl","com.zerodg.vwapi.controller"})
//sql映射
@MapperScan(value = "com.zerodg.vwdao.mapper")
@EnableScheduling
public class VwApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VwApiApplication.class, args);
	}

	@Bean
	public Docket buildDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())//调用下面的apiInfo()方法
				.select()
				//Controller所在路径
				.apis(RequestHandlerSelectors.basePackage("com.zerodg.vwapi.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * 构造ui信息
	 * @return
	 */
	public ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("ZD API")
				.description("zd 后台接口")
				.termsOfServiceUrl("www.baidu.com")
				.contact(new Contact("chenjy","www.baidu.com","1163005753@qq.com"))
				.version("0.0.1")
				.build();

	}

}

