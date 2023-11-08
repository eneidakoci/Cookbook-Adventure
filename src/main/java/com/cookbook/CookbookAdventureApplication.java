package com.cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication(scanBasePackages = "com.cookbook.domain.mapper")
public class CookbookAdventureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookbookAdventureApplication.class, args);
	}

}
