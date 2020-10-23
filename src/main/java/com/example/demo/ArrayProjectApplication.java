package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@EnableScheduling
@SpringBootApplication
public class ArrayProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(ArrayProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ArrayProjectApplication.class, args);
	}
		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}

		@Bean
		public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
			return args -> {
				reportsCurrentTime();
			};
		}

//		@Scheduled(fixedRate = 2000)
		public void reportsCurrentTime(){
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object1[]> response = restTemplate.getForEntity(
					"https://api.n.exchange/en/api/v1/price/BTCLTC/latest/?market_code=nex", Object1[].class);
			Object1[] object1 = response.getBody();
			log.info(Arrays.toString(object1));
		}

}
