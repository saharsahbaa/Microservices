package score.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableFeignClients
public class ApplicationScore {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationScore.class, args);
	}

}