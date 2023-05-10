package com.example.devintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevIntegrationApplication.class, args);
		System.out.println("Pull --> git ");
		System.out.println("Build --> maven");
		System.out.println(" Test --> maven + junit ");
		System.out.println(" publish -> nexus");



	}

}
