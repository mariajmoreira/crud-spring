package com.crud.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crud.crudspring.model.Alimento;
import com.crud.crudspring.repository.AlimentosRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(AlimentosRepository alimentosRepository){
		return args -> {
			alimentosRepository.deleteAll();

			Alimento a = new Alimento();
			a.setName("Arroz");
			a.setCategory("Grãos");
			alimentosRepository.save(a);
		};
	}

}
