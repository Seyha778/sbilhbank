package kh.com.sbilhbank.nbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbcApplication.class, args);
	}

}
