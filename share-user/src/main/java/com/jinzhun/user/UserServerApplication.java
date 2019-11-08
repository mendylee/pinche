package com.jinzhun.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jinzhun.common.annotation.EnableLoginArgResolver;

@EnableDiscoveryClient
@SpringBootApplication
@EnableLoginArgResolver
@EnableTransactionManagement
public class UserServerApplication {

	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(UserServerApplication.class, args);
	}

}
