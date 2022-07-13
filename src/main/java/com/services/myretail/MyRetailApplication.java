package com.services.myretail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MyRetailApplication {

	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);

	}

	@Bean
	public RestTemplate getRestClient() {
		return restTemplate = new RestTemplate ();
	}

}
