package com.ezbytes.hellowordservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RefreshScope
@EnableFeignClients
public class HellowordserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellowordserviceApplication.class, args);
	}

}
