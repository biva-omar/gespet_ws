package fr.formationspring.gespet.ws.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import fr.formationspring.gespet.ws.config.GespetWsConfig;

@SpringBootApplication
@Import({ GespetWsConfig.class })
public class GespetWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GespetWsApplication.class, args);
	}

}
