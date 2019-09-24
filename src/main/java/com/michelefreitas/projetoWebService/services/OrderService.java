package com.michelefreitas.projetoWebService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelefreitas.projetoWebService.entities.Order;
import com.michelefreitas.projetoWebService.repositories.OrderRepository;

//@Component - registra classe como componente do Spring e vai poder se injetado com @Autowired.
//Existe o @Repository para registrar repositório e tem o @Service para registrar um serviço

@Service
public class OrderService {
	
	//@Auto... para Spring faça injeção transparencia para o programador
	//OrderService dependency para OrderRepository(injeção dependencia do repository)
	@Autowired
	private OrderRepository repository;
	
	
	//retornar todos usuários do BD
	public List<Order> findAll(){
		return repository.findAll();
	}

	//recuperar usuário por id
	public Order findById(Long id) {
		//retorna obj tipo Optional
		Optional<Order> obj = repository.findById(id);
		//retorna obj do tipo Order que está dentro do Optional
		return obj.get();
	}
}
