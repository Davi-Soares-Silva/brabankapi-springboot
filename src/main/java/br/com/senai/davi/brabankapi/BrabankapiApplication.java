package br.com.senai.davi.brabankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * Criamos um novo projeto pelo start.spring.io
 * O importamos para o Eclipse
 * Conhecemos o arquivo pom.xml (gerencia as configurações de dependências e build)
 * Configuramos no arquivo application.properties
*/

@SpringBootApplication
public class BrabankapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrabankapiApplication.class, args);
		
	}

}
