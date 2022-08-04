package com.fsfb.branchregister.branchregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BranchregisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BranchregisterApplication.class, args);
	}

	/*@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}*/
}
