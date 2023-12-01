package com.crud.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.crud.crudspring.config.WebSecurityConfig;

//import com.crud.crudspring.model.Alimento;
//import com.crud.crudspring.repository.AlimentosRepository;
@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

/* 	@Bean
	CommandLineRunner initDatabase(AlimentosRepository alimentosRepository){
		return args -> {
			alimentosRepository.deleteAll();

			Food a = new Alimento();
			a.setName("Arroz");
			a.setCategory("Grãos");
			alimentosRepository.save(a);
		};
	} */

	/* 	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/foods").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/calculations/*").allowedOrigins("http://localhost:4200");
			}
		};
	} */



}
