package com.michelefreitas.projetoWebService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelefreitas.projetoWebService.entities.Product;
import com.michelefreitas.projetoWebService.repositories.ProductRepository;

//@Component - registra classe como componente do Spring e vai poder se injetado com @Autowired.
//Existe o @Repository para registrar repositório e tem o @Service para registrar um serviço

@Service
public class ProductService {
	
	//@Auto... para Spring faça injeção transparencia para o programador
	//ProductService dependency para ProductRepository(injeção dependencia do repository com @Autowired)
	@Autowired
	private ProductRepository repository;
	
	
	//retornar todos produtos do BD
	public List<Product> findAll(){
		return repository.findAll();
	}

	//recuperar produto por id
	public Product findById(Long id) {
		//retorna obj tipo Optional
		Optional<Product> obj = repository.findById(id);
		//retorna obj do tipo Product que está dentro do Optional
		return obj.get();
	}
}
