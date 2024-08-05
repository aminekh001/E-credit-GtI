package com.gti_e_credit.demande_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
public class DemandeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemandeServiceApplication.class, args);
	}

}
