package com.ezbytes.hellowordservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class HellowordserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellowordserviceApplication.class, args);
	}

}
