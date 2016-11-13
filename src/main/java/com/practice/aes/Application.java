package com.practice.aes;

import java.time.OffsetDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	// Application started time, used for IV key
	// this value changed reset/restart
	public final static OffsetDateTime UP_DATE = OffsetDateTime.now();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
