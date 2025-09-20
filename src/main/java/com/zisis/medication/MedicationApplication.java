package com.zisis.medication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicationApplication {
	static void main(String[] args) {
        System.setProperty("spring.profiles.active", "h2"); //tmp
        SpringApplication.run(MedicationApplication.class, args);
	}
}
