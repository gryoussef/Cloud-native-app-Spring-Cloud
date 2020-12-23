package ma.s.gcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SuiviClientsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuiviClientsApplication.class, args);
	}
}
