package com.service.romanconversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class RomanConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RomanConversionApplication.class, args);
	}

	@GetMapping(path = "/")
	public @ResponseBody String healthCheck() {
		log.info("Health check status call");
		return "Application is up and running!";
	}
}
