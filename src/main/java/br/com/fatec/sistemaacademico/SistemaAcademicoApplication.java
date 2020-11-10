package br.com.fatec.sistemaacademico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SistemaAcademicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaAcademicoApplication.class, args);
	}

}
