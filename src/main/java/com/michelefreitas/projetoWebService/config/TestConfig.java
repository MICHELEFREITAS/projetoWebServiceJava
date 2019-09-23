package com.michelefreitas.projetoWebService.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.michelefreitas.projetoWebService.entities.User;
import com.michelefreitas.projetoWebService.repositories.UserRepository;

//classe específica de configuração. Vai rodar configuração somente quandoe stiver perfil teste
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	//Spring resolve dependência
	@Autowired
	private UserRepository userRepository;

	//tudo que estiver dentro desse método será executado ao iniciar a aplicação
	@Override
	public void run(String... args) throws Exception {
		//id será gerado pelo banco de dados
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//acessa dados e salva os objetos no BD
		userRepository.saveAll(Arrays.asList(u1,u2));
	}

}
