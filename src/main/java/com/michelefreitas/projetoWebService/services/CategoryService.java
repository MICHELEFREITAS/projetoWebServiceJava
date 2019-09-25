package com.michelefreitas.projetoWebService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelefreitas.projetoWebService.entities.Category;
import com.michelefreitas.projetoWebService.repositories.CategoryRepository;

//@Component - registra classe como componente do Spring e vai poder se injetado com @Autowired.
//Existe o @Repository para registrar repositório e tem o @Service para registrar um serviço

@Service
public class CategoryService {
	
	//@Auto... para Spring faça injeção transparencia para o programador
	//CategoryService dependency para CategoryRepository(injeção dependencia do repository com @Autowired)
	@Autowired
	private CategoryRepository repository;
	
	
	//retornar todas categorias do BD
	public List<Category> findAll(){
		return repository.findAll();
	}

	//recuperar categoria por id
	public Category findById(Long id) {
		//retorna obj tipo Optional
		Optional<Category> obj = repository.findById(id);
		//retorna obj do tipo Category que está dentro do Optional
		return obj.get();
	}
}
