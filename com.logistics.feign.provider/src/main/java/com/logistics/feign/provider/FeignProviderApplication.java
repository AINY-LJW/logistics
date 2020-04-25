package com.logistics.feign.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//启动类中增加 @EnableDiscoveryClient 注解，声明这是一个 Eureka Client。服务提供者
@EnableDiscoveryClient 
@MapperScan("com.logistics.feign.provider.dao")
@ComponentScan(basePackages = {"com.logistics.*"})
@SpringBootApplication
/**
 * 
 * 简述部分:
 * 
 *
 * @author helantian
 * @version 2020年1月17日
 */
public class FeignProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignProviderApplication.class, args);
	}

}
