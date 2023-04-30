package com.ex.services.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootUploadCsvFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUploadCsvFilesApplication.class, args);
	}

}
