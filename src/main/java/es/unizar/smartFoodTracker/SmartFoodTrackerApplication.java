package es.unizar.smartFoodTracker;

import es.unizar.smartFoodTracker.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SmartFoodTrackerApplication extends SpringBootServletInitializer implements CommandLineRunner  {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SmartFoodTrackerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SmartFoodTrackerApplication.class, args);
	}

	@Override
	public void run(String... args){

	}
}
