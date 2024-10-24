package com.sec.leetcodesystem.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.sec.leetcodesystem")
@EntityScan(basePackages = "com.sec.leetcodesystem.entities")
@EnableJpaRepositories(basePackages = "com.sec.leetcodesystem.repository")
public class LeetcodesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeetcodesystemApplication.class, args);
	}

}
