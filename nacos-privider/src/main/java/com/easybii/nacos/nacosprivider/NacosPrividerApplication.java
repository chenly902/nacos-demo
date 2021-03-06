package com.easybii.nacos.nacosprivider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPrividerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosPrividerApplication.class, args);
	}

}
