package com.activity1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class Activity1BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(Activity1BackApplication.class, args);
	}

}
