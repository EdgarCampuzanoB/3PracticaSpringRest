package com.example.PracticaJavaRest;

import com.example.PracticaJavaRest.entity.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner runner(RestTemplate restTemplate) throws Exception{
		return args -> {
			Customer customer = new Customer();
			customer.setDni("A21");
			customer.setName("Edgar");
			ResponseEntity<Customer> entity2 = restTemplate
					.postForEntity("http://localhost:8080/customers", customer, Customer.class);

			ResponseEntity<Customer> entity = restTemplate
					.getForEntity("http://localhost:8080/customers", Customer.class);
			if (entity.getStatusCode().is2xxSuccessful()) {
				System.out.println(entity.toString());
			}else{
				System.out.println("Hubo un error en la peticion" + entity.getStatusCode());
			}
		};
	}

}
