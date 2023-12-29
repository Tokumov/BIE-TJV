package tjv.tokumshy_semestrialwork.kazakhcuisine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;

@SpringBootApplication
@RestController

public class KazakhcuisineApplication {

	public static void main(String[] args) {
		SpringApplication.run(KazakhcuisineApplication.class, args);
	}

}
