package br.com.felipeomachado.apitest1308pulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
public class ApiTest1308PulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTest1308PulseApplication.class, args);
	}

}
