package com.michelefreitas.projetoWebService.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.michelefreitas.projetoWebService.entities.Order;
import com.michelefreitas.projetoWebService.entities.User;
import com.michelefreitas.projetoWebService.repositories.OrderRepository;
import com.michelefreitas.projetoWebService.repositories.UserRepository;

//classe específica de configuração. Vai rodar configuração somente quandoe stiver perfil teste
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	//Spring resolve dependência
	@Autowired
	private UserRepository userRepository;
	
	//colocar alguns pedidos na carga inicial do BD
	@Autowired
	private OrderRepository orderRepository;

	//tudo que estiver dentro desse método será executado ao iniciar a aplicação
	@Override
	public void run(String... args) throws Exception {
		//id será gerado pelo banco de dados
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Instant padrão UTC greenwich no formato String
		//instanciando os pedidos passando o instant em String formato ISO 8601 e o 3º parametro o usuário
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);
		
		//acessa dados e salva os objetos no BD
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	}
	
	

}
